package com.example.recipeapp.services;

import com.example.recipeapp.model.Recipe;

public interface RecipesService {
    void readFile(String fileName);
    void parseFile();
    void addRecipe(Recipe recipe);
    Recipe getRecipe(int id);
}
