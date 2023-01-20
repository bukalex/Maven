package com.example.recipeapp.services;

import java.io.File;

public interface FileService {

    boolean saveToRecipeFile(String data);

    String readFromRecipeFile();

    boolean cleanRecipeFile();

    File getRecipeFile();
}
