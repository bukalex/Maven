package com.example.recipeapp.services.impl;

import com.example.recipeapp.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {
    @Value("${path.to.recipe.file}")
    private String recipeFilePath;
    @Value("${name.of.recipe.file}")
    private String recipeFileName;
    @Value("${path.to.TXT.recipe.file}")
    private String recipeTXTFilePath;
    @Value("${name.of.TXT.recipe.file}")
    private String recipeTXTFileName;

    @Override
    public boolean saveToRecipeFile(String data){
        try {
            cleanRecipeFile();
            Files.writeString(Path.of(recipeFilePath, recipeFileName), data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String readFromRecipeFile(){
        try {
            return Files.readString(Path.of(recipeFilePath, recipeFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanRecipeFile(){
        try {
            Path path = Path.of(recipeFilePath, recipeFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public File getRecipeFile(){
        return new File(recipeFilePath +"/"+ recipeFileName);
    }

    public File getTXTFile(String data){
        try {
            Path path = Path.of(recipeTXTFilePath, recipeTXTFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.writeString(path, data);
            return new File(recipeTXTFilePath +"/"+ recipeTXTFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
