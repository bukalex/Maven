package com.example.recipeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int amount;
    private String units;

    @Override
    public String toString() {
        return "name: " + name + "\n" +
                "amount: " + amount + "\n" +
                "units: " + units;
    }
}
