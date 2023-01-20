package com.example.recipeapp.Controllers;

import com.example.recipeapp.model.Recipe;
import com.example.recipeapp.services.impl.FileServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

import static com.example.recipeapp.Controllers.RecipesController.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final FileServiceImpl fileService = new FileServiceImpl();

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadFile() throws FileNotFoundException {
        File file = fileService.getRecipeFile();
        if (file.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.json\"")
                    .body(resource);
        } else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/export_txt")
    public ResponseEntity<InputStreamResource> downloadTXTFile() throws FileNotFoundException {
        String allRecipes = new String();
        for(Map.Entry<Integer, Recipe> recipe : recipesService.getRecipeMap().entrySet()){
            allRecipes += recipe.getKey() + "\n";
            allRecipes += recipe.getValue().toString() + "\n" + "\n";
        }
        File file = fileService.getTXTFile(allRecipes);
        if (file.exists()){
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipes.txt\"")
                    .body(resource);
        } else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(@RequestParam MultipartFile file){
        fileService.cleanRecipeFile();
        File recipeFile = fileService.getRecipeFile();
        try(FileOutputStream fos = new FileOutputStream(recipeFile)){
            IOUtils.copy(file.getInputStream(), fos);
            recipesService.readFromRecipeFile();
            return ResponseEntity.ok().build();
        } catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
