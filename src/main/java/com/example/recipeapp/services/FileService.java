package com.example.recipeapp.services;

public interface FileService {

    boolean saveToRecipeFile(String data);

    String readFromRecipeFile();

    boolean saveToIngredientFile(String data);

    String readFromIngredientFile();
}
