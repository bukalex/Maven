package com.example.recipeapp.services.impl;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.RecipesService;
import org.springframework.stereotype.Service;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private JSONArray recipeList;
    public void readFile(String fileName){
        JSONParser jsonParser = new JSONParser();
        try{
            FileReader reader = new FileReader(fileName);
            recipeList = (JSONArray) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Recipe> getRecipeMap() {
        return recipeMap;
    }

    @Override
    public void parseFile() {
        for(Object recipe : recipeList){
            Ingredient.setID(0);
            Map<Integer, Ingredient> ingredMap = new HashMap<>();
            for(Object ingred : (JSONArray) ((JSONObject)recipe).get("ingredients")){
                Ingredient newIngred = new Ingredient((String) ((JSONObject)ingred).get("name"),
                        (double) ((JSONObject)ingred).get("amount"),
                        (String) ((JSONObject)ingred).get("units"));
                ingredMap.put(Ingredient.ID++, newIngred);
            }

            Recipe newRecipe = new Recipe((String) ((JSONObject)recipe).get("title"),
                    (double) ((JSONObject)recipe).get("time"),
                    ingredMap,
                    (ArrayList<String>) ((JSONObject)recipe).get("instruction"));
            recipeMap.put(Recipe.ID++, newRecipe);
        }
    }

    @Override
    public void addRecipe(Recipe recipe) {
        recipeMap.put(Recipe.ID++, recipe);
    }

    @Override
    public Recipe getRecipe(int id) {
        return null;
    }
}
