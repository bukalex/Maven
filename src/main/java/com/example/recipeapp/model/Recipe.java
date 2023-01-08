package com.example.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

@AllArgsConstructor
public class Recipe {
    public static int ID = 0;
    private String title;
    private double time;
    private Map<Integer, Ingredient> ingreds;
    private ArrayList<String> steps;

    @Override
    public String toString() {
        String ingredString = new String();
        String stepString = new String();
        for(Map.Entry<Integer, Ingredient> ingred : ingreds.entrySet()){
            ingredString += ingred.getValue().toString() + "<br>" + "<br>";
        }
        for(String step : steps){
            stepString += step + "<br>";
        }
        return "title: " + title + "<br>" +
                "time: " + time + "<br>" +
                "ingredients: " + "<br>" + ingredString +
                "steps: " + "<br>" + stepString;
    }
}
