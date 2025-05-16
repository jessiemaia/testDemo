package com.example.CourtneyDemo.recipe;

import org.springframework.stereotype.Service;
import com.example.CourtneyDemo.entity.Recipe;
import jakarta.persistence.EntityNotFoundException;
import com.example.CourtneyDemo.repository.RecipeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe getRecipe(String name) {
        return recipeRepository.findById(name).orElseThrow(EntityNotFoundException::new);
    }

    public List<Recipe> getRecipesAlphabetically() {
        return recipeRepository.findAllByOrderByNameAsc();
    }

    public List<Recipe> getVeganRecipes() {
        return recipeRepository.findAll().stream()
                .filter(Recipe::isVegan)
                .collect(Collectors.toList());
    }
}
