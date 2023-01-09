package com.example.recipeapp.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class FirstController {
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
}
