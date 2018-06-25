package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import webdev.models.Food;
import webdev.models.Ingredient;
import webdev.models.Recipe;
import webdev.models.User;
import webdev.repositories.RecipeRepository;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class RecipeService {

    @Autowired
    @Qualifier("customRecipeRepository")
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
    
    @PutMapping("/api/recipe/{recipeId}/privatize") // #capitalism, this function is now 75% more efficient
    public Recipe privatizeRecipe(@PathVariable("recipeId") int recipeId, HttpSession session) {
    	User user = (User) session.getAttribute(("user"));
		if(user.isReputable()) {
			List<Recipe> createdRecipes = user.getCreatedRecipes();
			for(int i = 0; i < createdRecipes.size(); i++) {
				if(createdRecipes.get(i).getId() == recipeId) {
					Recipe r = createdRecipes.get(i);
					r.setPrivate(!r.isPrivate());
					r.setDietLabels(null);
					r.setIngredients(null);
					r.setSavedByUser(null);
					r.setReviews(null);
					return recipeRepository.save(r);
				}
			}
		}
    	return null;
    }
    
    @GetMapping("/api/recipe/{recipeId}/user")
    public User getCreatedByUser(@PathVariable("recipeId") int recipeId) {
    	Optional<Recipe> data = recipeRepository.findById(recipeId);
    	if(data.isPresent()) {
    		Recipe recipe = data.get();
    		User user = recipe.getCreatedByUser();
    		System.out.println("We found a user!");
    		return user;
    	}
    	System.out.println("couldn't find a user for recipe");
    	return null;
    }

    @GetMapping("/api/user/{uid}/recipe/saved")
    public Set<Recipe> findRecipesSavedByUser(@PathVariable("uid") int userId) {
        Optional<User> maybeUser = userRepository.findById(userId);
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            return user.getSavedRecipes();
        }
        return null;
    }

    @PostMapping("/api/recipe/create")
    public Recipe createRecipe(@RequestBody Recipe recipe, HttpSession httpSession) {
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
    	/*List<Food> foods;
    	for(Ingredient ingredient : recipe.getIngredients()) {
    		ingredient.getFood().getIngredients().add(ingredient);
    	}*/
    	System.out.println("Let's save this sucker!");
    	//System.out.println(recipe);
    	//System.out.println(recipe.getDietLabels());
    	//System.out.println(recipe.getIngredients());
    	if(recipe.getUri() == null) {
    		recipe.setCreatedByUser((User) httpSession.getAttribute("user"));
    	}
    	
        return recipeRepository.save(recipe);
    }
    
    @GetMapping("api/recipe/search/{query}/page/{page}")
    public List<Recipe> search(@PathVariable String query, @PathVariable int page) {
    	List<Recipe> recipes = (List<Recipe>) recipeRepository.findAll();
    	List<Recipe> matches = new ArrayList<Recipe>();
    	String[] searchTerms = query.split(" ");
    	
    	for(int i = 0; i < recipes.size(); i++) {
    		if(!recipes.get(i).isPrivate()) {
	    		boolean foundRecipe = false;
	    		List<Ingredient> ingredients = (List<Ingredient>) recipes.get(i).getIngredients();
	    		for(int j = 0; j < ingredients.size(); j++) {
	    			for(int k = 0; k < searchTerms.length; k++) {
	    				if(searchTerms[k].equals(ingredients.get(j).getFood().getLabel())) {
	    					matches.add(recipes.get(i));
	    					foundRecipe = true;
	    					break;
	    				}
	    			}
	    			if(foundRecipe) {
	    				break;
	    			}
	    		}
    		}
    	}
    	
    	int startIndex = 10 * (page - 1);
    	int endIndex = Math.min(startIndex + 10, matches.size());
    	
    	return matches.subList(startIndex, endIndex);
    }
    
    @PostMapping("/api/user/{uid}/recipe/save")
    public Recipe saveRecipe(@PathVariable("uid") int userId, @RequestBody Recipe recipe, HttpSession httpSession) {
        User sessionUser = (User) httpSession.getAttribute("user");
        Optional<User> maybeUser = userRepository.findById(userId);

        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            if (user.getId() == sessionUser.getId()) {
                Set<Recipe> recipes = user.getSavedRecipes();
                recipes.add(recipe);

                Set<User> recipeSaves = recipe.getSavedByUser();
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
    
    @PutMapping("/api/recipe/{recipeId}/endorse")
    public User endorseRecipe(@PathVariable("recipeId") int recipeId, HttpSession session) {
    	User user = (User) session.getAttribute("user");
    	if(user != null) {
    		if(user.isChef()) {
    			Optional<User> userFromDBData = userRepository.findById(user.getId());
    			if(userFromDBData.isPresent()) {
    				System.out.println("FUCK YEAHHHHHHHHHHHHHHHH");
    				User userFromDB = userFromDBData.get();
    				Optional<Recipe> data = recipeRepository.findById(recipeId);
    	    		if(data.isPresent()) {
    	    			System.out.println("DOUBLE FUCK YEAHHHHHHHHHHHH");
    	    			Recipe recipe = data.get();
    	    			userFromDB.endorseRecipe(recipe);
    	    			session.setAttribute("user", userFromDB);
    	    			return userRepository.save(userFromDB);
    	    		}
    			}
    		}
    	}
    	return null;
    }
    
    @GetMapping("api/recipe/{recipeId}/endorsedBy")
    public Set<User> recipeEndorsedBy(@PathVariable("recipeId") int recipeId) {
    	Optional data = recipeRepository.findById(recipeId);
    	if (data.isPresent()) {
    		Recipe recipe = (Recipe) data.get();
    		return recipe.getEndorsedByUser();
    	}
    	return null;
    }
    
    @PutMapping("/api/recipe/{recipeId}/save")
    public User saveRecipe(@PathVariable("recipeId") int recipeId, HttpSession session) {
    	User user = (User) session.getAttribute("user");
    	if(user != null) {
    		Optional<User> userFromDBData = userRepository.findById(user.getId());
    		if(userFromDBData.isPresent()) {
    			Optional<Recipe> data = recipeRepository.findById(recipeId);
    			User userFromDB = userFromDBData.get();
        		if(data.isPresent()) {
        			System.out.println("Well yo we're getting here haha");
        			Recipe recipe = data.get();
        			userFromDB.saveRecipe(recipe);
        			session.setAttribute("user", userFromDB);
        			return userRepository.save(userFromDB);
        		}
    		}
    	}
    	return null;
    }

    @DeleteMapping("/api/recipe/{rid}")
    public void deleteRecipe(@PathVariable("rid") int recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}
