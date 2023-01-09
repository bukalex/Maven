package com.example.recipeapp.Controllers;

import com.example.recipeapp.model.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.recipeapp.Controllers.RecipesController.recipesService;

@RestController
@RequestMapping("/recipes/{recipeID}/ingredients")
public class IngredientsController {
    @GetMapping("/get_ingredient/{id}")
    public String getIngredient(@PathVariable int recipeID, @PathVariable int id){
        if(recipesService.getRecipe(recipeID) == null || recipesService.getIngredient(recipeID, id) == null){
            return "Ничего не найдено!";
        }
        return recipesService.getIngredient(recipeID, id).toString();
    }

    @GetMapping("/get_all_ingredients")
    public String getAllIngredients(@PathVariable int recipeID){
        String allIngredients = new String();
        for(Map.Entry<Integer, Ingredient> ingredient : recipesService.getRecipe(recipeID).getIngredients().entrySet()){
            allIngredients += ingredient.getKey() + "\n";
            allIngredients += ingredient.getValue().toString() + "\n" + "\n";
        }
        return allIngredients;
    }

    @PostMapping("/add_ingredient")
    public ResponseEntity<Ingredient> addIngredient(@PathVariable int recipeID, @RequestBody Ingredient newIngredient){
        if(recipesService.getRecipe(recipeID) == null || newIngredient == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.addIngredient(recipeID, newIngredient);
        return ResponseEntity.ok().body(newIngredient);
    }

    @PutMapping("/edit_ingredient/{id}")
    public ResponseEntity<Ingredient> addRecipe(@PathVariable int recipeID, @PathVariable int id, @RequestBody Ingredient editedIngredient){
        if(recipesService.getRecipe(recipeID) == null || editedIngredient == null || recipesService.getIngredient(recipeID, id) == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.editIngredient(recipeID, id, editedIngredient);
        return ResponseEntity.ok().body(editedIngredient);
    }

    @DeleteMapping("/delete_ingredient/{id}")
    public ResponseEntity<Ingredient> deleteRecipe(@PathVariable int recipeID, @PathVariable int id){
        if(recipesService.getRecipe(recipeID) == null || recipesService.getIngredient(recipeID, id) == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.deleteIngredient(recipeID, id);
        return ResponseEntity.ok().build();
    }
}
