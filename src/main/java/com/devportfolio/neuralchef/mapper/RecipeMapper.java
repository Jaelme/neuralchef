package com.devportfolio.neuralchef.mapper;

import com.devportfolio.neuralchef.dto.*;
import com.devportfolio.neuralchef.model.*;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    public RecipeDTO toDTO(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeDTO dto = new RecipeDTO();
        dto.setId(recipe.getId());
        dto.setTitle(recipe.getTitle());
        dto.setDescription(recipe.getDescription());
        dto.setServings(recipe.getServings());
        dto.setPreparationTime(recipe.getPreparationTime());
        dto.setCookingTime(recipe.getCookingTime());
        dto.setInstructions(recipe.getInstructions());
        dto.setDifficulty(recipe.getDifficulty());
        dto.setCreatedAt(recipe.getCreatedAt());
        dto.setUpdatedAt(recipe.getUpdatedAt());

        if (recipe.getIngredients() != null) {
            dto.setIngredients(recipe.getIngredients().stream()
                    .map(this::toIngredientDTO)
                    .collect(Collectors.toList()));
        }

        if (recipe.getNutritionalInfo() != null) {
            dto.setNutritionalInfo(toNutritionalInfoDTO(recipe.getNutritionalInfo()));
        }

        return dto;
    }

    public Recipe toEntity(CreateRecipeRequest request) {
        if (request == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setServings(request.getServings());
        recipe.setPreparationTime(request.getPreparationTime());
        recipe.setCookingTime(request.getCookingTime());
        recipe.setInstructions(request.getInstructions());
        recipe.setDifficulty(request.getDifficulty());

        if (request.getIngredients() != null) {
            request.getIngredients().forEach(ingredientDTO -> {
                Ingredient ingredient = toIngredientEntity(ingredientDTO);
                recipe.addIngredient(ingredient);
            });
        }

        return recipe;
    }

    public void updateEntityFromDTO(Recipe recipe, UpdateRecipeRequest request) {
        if (request.getTitle() != null) {
            recipe.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            recipe.setDescription(request.getDescription());
        }
        if (request.getServings() != null) {
            recipe.setServings(request.getServings());
        }
        if (request.getPreparationTime() != null) {
            recipe.setPreparationTime(request.getPreparationTime());
        }
        if (request.getCookingTime() != null) {
            recipe.setCookingTime(request.getCookingTime());
        }
        if (request.getInstructions() != null) {
            recipe.setInstructions(request.getInstructions());
        }
        if (request.getDifficulty() != null) {
            recipe.setDifficulty(request.getDifficulty());
        }
        if (request.getIngredients() != null) {
            recipe.getIngredients().clear();
            request.getIngredients().forEach(ingredientDTO -> {
                Ingredient ingredient = toIngredientEntity(ingredientDTO);
                recipe.addIngredient(ingredient);
            });
        }
    }

    public IngredientDTO toIngredientDTO(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientDTO dto = new IngredientDTO();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());
        dto.setQuantity(ingredient.getQuantity());
        dto.setUnit(ingredient.getUnit());
        return dto;
    }

    public Ingredient toIngredientEntity(IngredientDTO dto) {
        if (dto == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(dto.getId());
        ingredient.setName(dto.getName());
        ingredient.setQuantity(dto.getQuantity());
        ingredient.setUnit(dto.getUnit());
        return ingredient;
    }

    public NutritionalInfoDTO toNutritionalInfoDTO(NutritionalInfo nutritionalInfo) {
        if (nutritionalInfo == null) {
            return null;
        }

        NutritionalInfoDTO dto = new NutritionalInfoDTO();
        dto.setId(nutritionalInfo.getId());
        dto.setCalories(nutritionalInfo.getCalories());
        dto.setProtein(nutritionalInfo.getProtein());
        dto.setCarbohydrates(nutritionalInfo.getCarbohydrates());
        dto.setFat(nutritionalInfo.getFat());
        dto.setFiber(nutritionalInfo.getFiber());
        dto.setSugar(nutritionalInfo.getSugar());
        dto.setSodium(nutritionalInfo.getSodium());
        return dto;
    }
}
