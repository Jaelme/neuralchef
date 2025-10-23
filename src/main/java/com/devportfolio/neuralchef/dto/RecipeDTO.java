package com.devportfolio.neuralchef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private Long id;
    private String title;
    private String description;
    private Integer servings;
    private Integer preparationTime;
    private Integer cookingTime;
    private String instructions;
    private String difficulty;
    private List<IngredientDTO> ingredients;
    private NutritionalInfoDTO nutritionalInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
