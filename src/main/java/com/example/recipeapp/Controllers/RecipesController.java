package com.example.recipeapp.Controllers;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.impl.RecipesServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    public static RecipesServiceImpl recipesService = new RecipesServiceImpl();

    @GetMapping("/get_recipe/{id}")
    public String getRecipe(@PathVariable int id){
        if(recipesService.getRecipe(id) == null){
            return "Ничего не найдено!";
        }
        return recipesService.getRecipe(id).toString();
    }

    @GetMapping("/get_all_recipes")
    public String getAllRecipes(){
        String allRecipes = new String();
        for(Map.Entry<Integer, Recipe> recipe : recipesService.getRecipeMap().entrySet()){
            allRecipes += recipe.getKey() + "\n";
            allRecipes += recipe.getValue().toString() + "\n" + "\n";
        }
        return allRecipes;
    }

    @PostMapping("/add_recipe")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe newRecipe){
        if(newRecipe == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.addRecipe(newRecipe);
        return ResponseEntity.ok().body(newRecipe);
    }

    @PutMapping("/edit_recipe/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe editedRecipe){
        if(editedRecipe == null || recipesService.getRecipe(id) == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.editRecipe(id, editedRecipe);
        return ResponseEntity.ok().body(editedRecipe);
    }

    @DeleteMapping("/delete_recipe/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int id){
        if(recipesService.getRecipe(id) == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }
}
