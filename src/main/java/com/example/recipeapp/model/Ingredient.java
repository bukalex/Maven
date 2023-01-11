package com.example.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String name;
    private double amount;
    private String units;

    @Override
    public String toString() {
        return "name: " + name + "\n" +
                "amount: " + amount + "\n" +
                "units: " + units;
    }
}
