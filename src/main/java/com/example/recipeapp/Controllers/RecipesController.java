package com.example.recipeapp.Controllers;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.RecipesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD-операции для работы с рецептами")
public class RecipesController {
    @Autowired
    private RecipesService recipesService;

    @GetMapping("/{id}")
    @Operation(summary = "Поиск рецепта по идентификатору")
    public String getRecipe(@PathVariable int id){
        if(recipesService.getRecipe(id) == null){
            return "Ничего не найдено!";
        }
        return recipesService.getRecipe(id).toString();
    }

    @GetMapping()
    @Operation(summary = "Поиск всех рецептов")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        return ResponseEntity.ok().body(recipesService.getAllRecipes());
    }

    @PostMapping()
    @Operation(summary = "Добавление нового рецепта")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe newRecipe){
        if(newRecipe == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.addRecipe(newRecipe);
        return ResponseEntity.ok().body(newRecipe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование рецепта по идентификатору")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe editedRecipe){
        if(editedRecipe == null || recipesService.getRecipe(id) == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.editRecipe(id, editedRecipe);
        return ResponseEntity.ok().body(editedRecipe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта по идентификатору")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable int id){
        if(recipesService.getRecipe(id) == null){
            return ResponseEntity.notFound().build();
        }
        recipesService.deleteRecipe(id);
        return ResponseEntity.ok().build();
    }
}
