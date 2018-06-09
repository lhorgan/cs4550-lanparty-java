package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import webdev.models.Recipe;
import webdev.repositories.RecipeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/api/recipe")
    public List<Recipe> findAllRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @GetMapping("/api/recipe/{recipeId}")
    public Recipe findRecipeById(@PathVariable("recipeId") int recipeId) {
        Optional<Recipe> maybeRecipe = recipeRepository.findById(recipeId);
        if (maybeRecipe.isPresent()) {
            return maybeRecipe.get();
        }
        return null;
    }
}
