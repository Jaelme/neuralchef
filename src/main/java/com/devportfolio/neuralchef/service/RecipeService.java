package com.devportfolio.neuralchef.service;

import com.devportfolio.neuralchef.dto.*;
import com.devportfolio.neuralchef.exception.ResourceNotFoundException;
import com.devportfolio.neuralchef.mapper.RecipeMapper;
import com.devportfolio.neuralchef.model.NutritionalInfo;
import com.devportfolio.neuralchef.model.Recipe;
import com.devportfolio.neuralchef.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final NutritionalAnalysisService nutritionalAnalysisService;

    public RecipeDTO createRecipe(CreateRecipeRequest request) {
        Recipe recipe = recipeMapper.toEntity(request);

        // Berechne Nährwertinformationen
        NutritionalInfo nutritionalInfo = nutritionalAnalysisService.calculateNutritionalInfo(
                recipe.getIngredients(),
                recipe.getServings()
        );
        recipe.setNutritionalInfo(nutritionalInfo);

        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeMapper.toDTO(savedRecipe);
    }

    @Transactional(readOnly = true)
    public RecipeDTO getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rezept", "id", id));
        return recipeMapper.toDTO(recipe);
    }

    @Transactional(readOnly = true)
    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecipeDTO> searchRecipesByTitle(String title) {
        return recipeRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecipeDTO> getRecipesByDifficulty(String difficulty) {
        return recipeRepository.findByDifficulty(difficulty).stream()
                .map(recipeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RecipeDTO updateRecipe(Long id, UpdateRecipeRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rezept", "id", id));

        recipeMapper.updateEntityFromDTO(recipe, request);

        // Aktualisiere Nährwertinformationen wenn Zutaten geändert wurden
        if (request.getIngredients() != null || request.getServings() != null) {
            NutritionalInfo nutritionalInfo = nutritionalAnalysisService.calculateNutritionalInfo(
                    recipe.getIngredients(),
                    recipe.getServings()
            );
            recipe.setNutritionalInfo(nutritionalInfo);
        }

        Recipe updatedRecipe = recipeRepository.save(recipe);
        return recipeMapper.toDTO(updatedRecipe);
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rezept", "id", id));
        recipeRepository.delete(recipe);
    }

    @Transactional(readOnly = true)
    public NutritionalInfoDTO getNutritionalInfo(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Rezept", "id", recipeId));
        return recipeMapper.toNutritionalInfoDTO(recipe.getNutritionalInfo());
    }
}
