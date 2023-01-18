package com.example.recipeapp.services.impl;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.FileService;
import com.example.recipeapp.services.RecipesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class RecipesServiceImpl implements RecipesService {
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private Integer aaa = 10;

    @Autowired
    private FileService fileService;


    @PostConstruct
    public void init(){
        readFromRecipeFile();
    }

    @Override
    public void editRecipe(int id, Recipe recipe){
        recipeMap.put(id, recipe);
        saveToRecipeFile();
    }

    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(Recipe.ID++, recipe);
        saveToRecipeFile();
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return (List<Recipe>) recipeMap.values();
    }

    @Override
    public void deleteRecipe(int id){
        recipeMap.remove(id);
        saveToRecipeFile();
    }

    @Override
    public void addIngredient(int recipeID, Ingredient ingredient){
        Map<Integer, Ingredient> ingredients = recipeMap.get(recipeID).getIngredients();
        ingredients.put(Collections.max(ingredients.keySet()) + 1, ingredient);
        saveToRecipeFile();
    }

    @Override
    public Ingredient getIngredient(int recipeID, int id){
        return recipeMap.get(recipeID).getIngredients().get(id);
    }

    @Override
    public void editIngredient(int recipeID, int id, Ingredient ingredient){
        recipeMap.get(recipeID).getIngredients().put(id, ingredient);
        saveToRecipeFile();
    }

    @Override
    public void deleteIngredient(int recipeID, int id){
        recipeMap.get(recipeID).getIngredients().remove(id);
        saveToRecipeFile();
    }


    private void saveToRecipeFile(){
        try {
            String data =new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToRecipeFile(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void readFromRecipeFile(){
        try {
            String data = fileService.readFromRecipeFile();
            recipeMap = new ObjectMapper().readValue(data, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
