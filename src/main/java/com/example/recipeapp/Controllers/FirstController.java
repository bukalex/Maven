package com.example.recipeapp.Controllers;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.impl.RecipesServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {
    RecipesServiceImpl recipesService = new RecipesServiceImpl();

    @GetMapping
    public String itWorks(){
        return "It works!";
    }

    @GetMapping("/info")
    public String infoPage(){
        return "Имя: " + "Алексей" + "<br>" +
                "Название проекта: " + "Рецепты" + "<br>" +
                "Дата создания проекта: " + "27.12.2022" + "<br>" +
                "Описание проекта: " + "Использован язык Java";
    }

    @GetMapping("/get_recipe")
    public String getRecipe(@RequestParam int id){
        recipesService.readFile(System.getProperty("user.dir") + "/src/main/java/com/example/recipeapp/services/impl/Recipes.json");
        recipesService.parseFile();
        if(id < recipesService.getRecipeMap().size()){
            return recipesService.getRecipeMap().get(id).toString();
        }else{
            return "Error";
        }
    }

    @PostMapping ("/add_recipe")
    public void addRecipe(@RequestBody Recipe newRecipe){
        recipesService.addRecipe(newRecipe);
    }
}
