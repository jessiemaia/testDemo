package com.example.CourtneyDemo.recipe;

import com.example.CourtneyDemo.entity.Recipe;
import com.example.CourtneyDemo.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    @InjectMocks
    RecipeService subject;

    @Test
    void getRecipe() {
        Recipe expected = Recipe.builder().build();
        when(recipeRepository.findById(anyString())).thenReturn(Optional.of(expected));

        Recipe actual = subject.getRecipe("chocolate chip cookies");

        verify(recipeRepository).findById("chocolate chip cookies");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getRecipeThrowsException() {
        when(recipeRepository.findById("")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> subject.getRecipe(""))
                .isInstanceOf(EntityNotFoundException.class);

        verify(recipeRepository).findById("");
    }

    @Test
    void getRecipesAlphabetically() {
        List<Recipe> mockReturn = List.of(
                Recipe.builder().name("alphabet soup").build(),
                Recipe.builder().name("zebra cake").build()
        );
        when(recipeRepository.findAllByOrderByNameAsc()).thenReturn(mockReturn);

        List<Recipe> expected = List.of(Recipe.builder().name("alphabet soup").build(), Recipe.builder().name("zebra cake").build());
        List<Recipe> actual = subject.getRecipesAlphabetically();

        verify(recipeRepository).findAllByOrderByNameAsc();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllVeganRecipes() {
        when(recipeRepository.findAll()).thenReturn(List.of(
                Recipe.builder().name("turkey Chili").isVegan(false).build(),
                Recipe.builder().name("tofu Stir Fry").isVegan(true).build()
        ));

        List<Recipe> actual = subject.getVeganRecipes();

        List<Recipe> expected = List.of(
                Recipe.builder().name("tofu Stir Fry").isVegan(true).build()
        );

        assertThat(actual).isEqualTo(expected);
    }
}