package com.devportfolio.neuralchef.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRecipeRequest {

    @Size(min = 3, max = 200, message = "Titel muss zwischen 3 und 200 Zeichen lang sein")
    private String title;

    @Size(max = 2000, message = "Beschreibung darf maximal 2000 Zeichen lang sein")
    private String description;

    @Min(value = 1, message = "Mindestens 1 Portion erforderlich")
    @Max(value = 50, message = "Maximal 50 Portionen erlaubt")
    private Integer servings;

    @Min(value = 1, message = "Zubereitungszeit muss mindestens 1 Minute sein")
    private Integer preparationTime;

    @Min(value = 0, message = "Kochzeit darf nicht negativ sein")
    private Integer cookingTime;

    @Size(min = 10, max = 5000, message = "Anleitung muss zwischen 10 und 5000 Zeichen lang sein")
    private String instructions;

    @Pattern(regexp = "EINFACH|MITTEL|SCHWER", message = "Schwierigkeitsgrad muss EINFACH, MITTEL oder SCHWER sein")
    private String difficulty;

    @Valid
    private List<IngredientDTO> ingredients;
}
