package com.example.recipeapp.services.impl;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.RecipesService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class RecipesServiceImpl implements RecipesService {
    private Map<Integer, Recipe> recipeMap = new HashMap<>();

    @Override
    public void editRecipe(int id, Recipe recipe){
        recipeMap.put(id, recipe);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(Recipe.ID++, recipe);
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }

    @Override
    public void deleteRecipe(int id){
        recipeMap.remove(id);
    }

    @Override
    public void addIngredient(int recipeID, Ingredient ingredient){
        Map<Integer, Ingredient> ingredients = recipeMap.get(recipeID).getIngredients();
        ingredients.put(Collections.max(ingredients.keySet()) + 1, ingredient);
    }

    @Override
    public Ingredient getIngredient(int recipeID, int id){
        return recipeMap.get(recipeID).getIngredients().get(id);
    }

    @Override
    public void editIngredient(int recipeID, int id, Ingredient ingredient){
        recipeMap.get(recipeID).getIngredients().put(id, ingredient);
    }

    @Override
    public void deleteIngredient(int recipeID, int id){
        recipeMap.get(recipeID).getIngredients().remove(id);
    }
}
