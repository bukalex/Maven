package com.example.recipeapp.services;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;

public interface RecipesService {
    void editRecipe(int id, Recipe recipe);

    void addRecipe(Recipe recipe);
    Recipe getRecipe(int id);

    void deleteRecipe(int id);

    void addIngredient(int recipeID, Ingredient ingredient);

    Ingredient getIngredient(int recipeID, int id);

    void editIngredient(int recipeID, int id, Ingredient ingredient);

    void deleteIngredient(int recipeID, int id);
}
