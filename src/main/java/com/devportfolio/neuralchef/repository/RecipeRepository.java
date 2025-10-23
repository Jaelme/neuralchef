package com.devportfolio.neuralchef.repository;

import com.devportfolio.neuralchef.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByTitleContainingIgnoreCase(String title);

    List<Recipe> findByDifficulty(String difficulty);

    List<Recipe> findByServings(Integer servings);
}
