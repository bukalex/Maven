package com.example.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    public static int ID = 0;
    private String title;
    private int time;
    private Map<Integer, Ingredient> ingredients = new HashMap<>();
    private ArrayList<String> instruction;

    @Override
    public String toString() {
        String ingredString = new String();
        String stepString = new String();
        for(Map.Entry<Integer, Ingredient> ingred : ingredients.entrySet()){
            ingredString += ingred.getValue().toString() + "\n" + "\n";
        }
        for(String step : instruction){
            stepString += step + "\n";
        }
        return "title: " + title + "\n" +
                "time: " + time + "\n" +
                "ingredients: " + "\n" + ingredString +
                "steps: " + "\n" + stepString;
    }
}
