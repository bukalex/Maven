package com.example.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class Ingredient {
    public static int ID = 0;
    private String name;
    private double amount;
    private String units;

    public static void setID(int ID) {
        Ingredient.ID = ID;
    }

    @Override
    public String toString() {
        return "name: " + name + "<br>" +
                "amount: " + amount + "<br>" +
                "units: " + units;
    }
}