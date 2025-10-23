package com.devportfolio.neuralchef.service;

import com.devportfolio.neuralchef.model.Ingredient;
import com.devportfolio.neuralchef.model.NutritionalInfo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Vereinfachter Service zur Nährwertberechnung
 * In einer echten Anwendung würde dies mit einer externen API
 * oder einer umfangreichen Nährwertdatenbank arbeiten
 */
@Service
public class NutritionalAnalysisService {

    // Vereinfachte Nährwerttabelle (pro 100g/100ml)
    private static final Map<String, NutritionalValues> NUTRITION_DATABASE = new HashMap<>();

    static {
        // Beispiel-Nährwerte
        NUTRITION_DATABASE.put("mehl", new NutritionalValues(364, 10.3, 72.0, 1.0, 3.2, 0.3, 2));
        NUTRITION_DATABASE.put("zucker", new NutritionalValues(387, 0, 99.8, 0, 0, 99.8, 1));
        NUTRITION_DATABASE.put("butter", new NutritionalValues(717, 0.7, 0.1, 81.1, 0, 0.1, 643));
        NUTRITION_DATABASE.put("ei", new NutritionalValues(155, 13.0, 1.1, 11.0, 0, 1.1, 124));
        NUTRITION_DATABASE.put("milch", new NutritionalValues(64, 3.4, 5.0, 3.6, 0, 5.0, 50));
        NUTRITION_DATABASE.put("hähnchenbrust", new NutritionalValues(165, 31.0, 0, 3.6, 0, 0, 74));
        NUTRITION_DATABASE.put("reis", new NutritionalValues(130, 2.7, 28.0, 0.3, 0.4, 0.1, 1));
        NUTRITION_DATABASE.put("kartoffel", new NutritionalValues(77, 2.0, 17.0, 0.1, 2.1, 0.8, 6));
        NUTRITION_DATABASE.put("tomate", new NutritionalValues(18, 0.9, 3.9, 0.2, 1.2, 2.6, 5));
        NUTRITION_DATABASE.put("zwiebel", new NutritionalValues(40, 1.1, 9.3, 0.1, 1.7, 4.2, 4));
        NUTRITION_DATABASE.put("knoblauch", new NutritionalValues(149, 6.4, 33.1, 0.5, 2.1, 1.0, 17));
        NUTRITION_DATABASE.put("olivenöl", new NutritionalValues(884, 0, 0, 100, 0, 0, 2));
        NUTRITION_DATABASE.put("salz", new NutritionalValues(0, 0, 0, 0, 0, 0, 38758));
    }

    public NutritionalInfo calculateNutritionalInfo(List<Ingredient> ingredients, Integer servings) {
        double totalCalories = 0;
        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFat = 0;
        double totalFiber = 0;
        double totalSugar = 0;
        double totalSodium = 0;

        for (Ingredient ingredient : ingredients) {
            String ingredientName = ingredient.getName().toLowerCase();
            double quantity = convertToGrams(ingredient.getQuantity(), ingredient.getUnit());

            // Suche nach passendem Nährwert in der Datenbank
            NutritionalValues nutritionalValues = findNutritionalValues(ingredientName);

            if (nutritionalValues != null) {
                // Berechne Nährwerte basierend auf der Menge (pro 100g)
                double factor = quantity / 100.0;
                totalCalories += nutritionalValues.calories * factor;
                totalProtein += nutritionalValues.protein * factor;
                totalCarbs += nutritionalValues.carbohydrates * factor;
                totalFat += nutritionalValues.fat * factor;
                totalFiber += nutritionalValues.fiber * factor;
                totalSugar += nutritionalValues.sugar * factor;
                totalSodium += nutritionalValues.sodium * factor;
            }
        }

        // Nährwerte pro Portion
        NutritionalInfo info = new NutritionalInfo();
        info.setCalories(Math.round(totalCalories / servings * 10.0) / 10.0);
        info.setProtein(Math.round(totalProtein / servings * 10.0) / 10.0);
        info.setCarbohydrates(Math.round(totalCarbs / servings * 10.0) / 10.0);
        info.setFat(Math.round(totalFat / servings * 10.0) / 10.0);
        info.setFiber(Math.round(totalFiber / servings * 10.0) / 10.0);
        info.setSugar(Math.round(totalSugar / servings * 10.0) / 10.0);
        info.setSodium(Math.round(totalSodium / servings * 10.0) / 10.0);

        return info;
    }

    private NutritionalValues findNutritionalValues(String ingredientName) {
        // Exakte Suche
        if (NUTRITION_DATABASE.containsKey(ingredientName)) {
            return NUTRITION_DATABASE.get(ingredientName);
        }

        // Teilstring-Suche
        for (Map.Entry<String, NutritionalValues> entry : NUTRITION_DATABASE.entrySet()) {
            if (ingredientName.contains(entry.getKey()) || entry.getKey().contains(ingredientName)) {
                return entry.getValue();
            }
        }

        // Standard-Nährwerte wenn nichts gefunden wurde
        return new NutritionalValues(100, 5, 10, 5, 1, 2, 50);
    }

    private double convertToGrams(double quantity, String unit) {
        return switch (unit.toLowerCase()) {
            case "kg" -> quantity * 1000;
            case "ml" -> quantity; // Vereinfachung: ml = g
            case "l" -> quantity * 1000;
            case "tl", "teelöffel" -> quantity * 5;
            case "el", "esslöffel" -> quantity * 15;
            case "stück" -> quantity * 100; // Durchschnittliches Gewicht
            case "prise" -> quantity * 0.5;
            default -> quantity; // Standardmäßig in Gramm
        };
    }

    // Innere Klasse für Nährwerte
    private static class NutritionalValues {
        double calories;
        double protein;
        double carbohydrates;
        double fat;
        double fiber;
        double sugar;
        double sodium;

        NutritionalValues(double calories, double protein, double carbohydrates,
                         double fat, double fiber, double sugar, double sodium) {
            this.calories = calories;
            this.protein = protein;
            this.carbohydrates = carbohydrates;
            this.fat = fat;
            this.fiber = fiber;
            this.sugar = sugar;
            this.sodium = sodium;
        }
    }
}
