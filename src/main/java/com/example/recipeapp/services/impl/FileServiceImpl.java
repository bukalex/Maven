package com.example.recipeapp.services.impl;

import com.example.recipeapp.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    //@Value("${path.to.recipe.file}")
    //private String recipeFilePath;
    //@Value("${name.of.recipe.file}")
    //private String recipeFileName;

    private String recipeFilePath = "src/main/resources";
    private String recipeFileName = "recipe.json";

    //@Value("${path.to.ingredient.file}")
    //private String ingredientFilePath;
    //@Value("${name.of.ingredient.file}")
    //private String ingredientFileName;

    private String ingredientFilePath = "src/main/resources";
    private String ingredientFileName = "ingredient.json";

    @Override
    public boolean saveToRecipeFile(String data){
        try {
            cleanFile(recipeFilePath, recipeFileName);
            Files.writeString(Path.of(recipeFilePath, recipeFileName), data);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromRecipeFile(){
        try {
            return Files.readString(Path.of(recipeFilePath, recipeFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveToIngredientFile(String data){
        try {
            cleanFile(ingredientFilePath, ingredientFileName);
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), data);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromIngredientFile(){
        try {
            return Files.readString(Path.of(ingredientFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanFile(String filePath, String fileName){
        try {
            Path path = Path.of(filePath, fileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
