package com.devportfolio.neuralchef.controller;

import com.devportfolio.neuralchef.dto.*;
import com.devportfolio.neuralchef.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody CreateRecipeRequest request) {
        RecipeDTO createdRecipe = recipeService.createRecipe(request);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id) {
        RecipeDTO recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getAllRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String difficulty) {

        List<RecipeDTO> recipes;

        if (title != null && !title.isEmpty()) {
            recipes = recipeService.searchRecipesByTitle(title);
        } else if (difficulty != null && !difficulty.isEmpty()) {
            recipes = recipeService.getRecipesByDifficulty(difficulty);
        } else {
            recipes = recipeService.getAllRecipes();
        }

        return ResponseEntity.ok(recipes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRecipeRequest request) {
        RecipeDTO updatedRecipe = recipeService.updateRecipe(id, request);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/nutrition")
    public ResponseEntity<NutritionalInfoDTO> getNutritionalInfo(@PathVariable Long id) {
        NutritionalInfoDTO nutritionalInfo = recipeService.getNutritionalInfo(id);
        return ResponseEntity.ok(nutritionalInfo);
    }
}
