package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Recipe;
import webdev.models.User;
import webdev.repositories.RecipeRepository;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

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

    @GetMapping("/api/user/{uid}/recipe/created")
    public List<Recipe> findRecipesCreatedByUser(@PathVariable("uid") int userId) {
        Optional<User> maybeUser = userRepository.findById(userId);
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            return user.getCreatedRecipes();
        }
        return null;
    }

    @GetMapping("/api/user/{uid}/recipe/saved")
    public List<Recipe> findRecipesSavedByUser(@PathVariable("uid") int userId) {
        Optional<User> maybeUser = userRepository.findById(userId);
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            return user.getSavedRecipes();
        }
        return null;
    }

    @PostMapping("/api/user/{uid}/recipe/create")
    public Recipe createRecipe(@PathVariable("uid") int userId, @RequestBody Recipe recipe, HttpSession httpSession) {
        /*User sessionUser = (User) httpSession.getAttribute("user");
        Optional<User> maybeUser = userRepository.findById(userId);

        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            if (user.getId() == sessionUser.getId()) {
                List<Recipe> recipes = user.getCreatedRecipes();
                recipes.add(recipe);
                recipe.setCreatedByUser(user);
                user.setCreatedRecipes(recipes);
                userRepository.save(user);
                recipeRepository.save(recipe);
                return recipe;
            }
        }
        return null;*/
      return recipeRepository.save(recipe);
    }

    @PostMapping("/api/user/{uid}/recipe/save")
    public Recipe saveRecipe(@PathVariable("uid") int userId, @RequestBody Recipe recipe, HttpSession httpSession) {
        User sessionUser = (User) httpSession.getAttribute("user");
        Optional<User> maybeUser = userRepository.findById(userId);

        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            if (user.getId() == sessionUser.getId()) {
                List<Recipe> recipes = user.getSavedRecipes();
                recipes.add(recipe);

                List<User> recipeSaves = recipe.getSavedByUser();
                recipeSaves.add(user);

                recipe.setSavedByUser(recipeSaves);
                user.setSavedRecipes(recipes);
                userRepository.save(user);
                recipeRepository.save(recipe);
                return recipe;
            }
        }
        return null;
    }

    @DeleteMapping("/api/recipe/{rid}")
    public void deleteRecipe(@PathVariable("rid") int recipeId) {
        recipeRepository.deleteById(recipeId);
    }


}
