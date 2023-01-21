package com.example.recipeapp.Controllers;

import com.example.recipeapp.model.Ingredient;
import com.example.recipeapp.services.RecipesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipes/{recipeID}/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD-операции для работы с ингредиентами")
public class IngredientsController {
    @Autowired
    private RecipesService recipesService;

    @GetMapping("/{id}")
    @Operation(summary = "Поиск ингредиента по идентификатору")
    public String getIngredient(@PathVariable int recipeID, @PathVariable int id){
        if(recipesService.getRecipe(recipeID) == null || recipesService.getIngredient(recipeID, id) == null){
            return "Ничего не найдено!";
        }
        return recipesService.getIngredient(recipeID, id).toString();
    }

    @GetMapping()
    @Operation(summary = "Поиск всех ингредиентов")
    public String getAllIngredients(@PathVariable int recipeID){
        String allIngredients = new String();
        for(Map.Entry<Integer, Ingredient> ingredient : recipesService.getRecipe(recipeID).getIngredients().entrySet()){
            allIngredients += ingredient.getKey() + "\n";
            allIngredients += ingredient.getValue().toString() + "\n" + "\n";
        }
        return allIngredients;
    }

    @PostMapping()
    @Operation(summary = "Добавление нового ингредиента")
    public ResponseEntity<Ingredient> addIngredient(@PathVariable int recipeID, @RequestBody Ingredient newIngredient){
        if(recipesService.getRecipe(recipeID) == null || newIngredient == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.addIngredient(recipeID, newIngredient);
        return ResponseEntity.ok().body(newIngredient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента по идентификатору")
    public ResponseEntity<Ingredient> addRecipe(@PathVariable int recipeID, @PathVariable int id, @RequestBody Ingredient editedIngredient){
        if(recipesService.getRecipe(recipeID) == null || editedIngredient == null || recipesService.getIngredient(recipeID, id) == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.editIngredient(recipeID, id, editedIngredient);
        return ResponseEntity.ok().body(editedIngredient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента по идентификатору")
    public ResponseEntity<Ingredient> deleteRecipe(@PathVariable int recipeID, @PathVariable int id){
        if(recipesService.getRecipe(recipeID) == null || recipesService.getIngredient(recipeID, id) == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.deleteIngredient(recipeID, id);
        return ResponseEntity.ok().build();
    }
}
