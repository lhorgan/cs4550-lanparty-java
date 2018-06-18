package webdev.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import webdev.enumerations.DietLabel;
import webdev.models.Food;
import webdev.models.Ingredient;
import webdev.models.Measure;
import webdev.models.Recipe;

@Repository("customRecipeRepository")
public class CustomRecipeRepository implements RecipeRepository {
	@Autowired
    @Qualifier("recipeRepository") // inject Spring implementation here
    private RecipeRepository recipeRepository;
	
	@Autowired
    @Qualifier("customFoodRepository") // inject Spring implementation here
    private FoodRepository foodRepository;
	
	@Autowired
    //@Qualifier("customMeasureRepository") // inject Spring implementation here
    private MeasureRepository measureRepository;
	
	@Override
	public long count() {
		return recipeRepository.count();
	}

	@Override
	public void delete(Recipe recipe) {
		recipeRepository.delete(recipe);
	}

	@Override
	public void deleteAll() {
		recipeRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Recipe> recipes) {
		recipeRepository.deleteAll(recipes);
	}

	@Override
	public void deleteById(Integer id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return recipeRepository.existsById(id);
	}

	@Override
	public Iterable<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	@Override
	public Iterable<Recipe> findAllById(Iterable<Integer> ids) {
		return recipeRepository.findAllById(ids);
	}

	@Override
	public Optional<Recipe> findById(Integer id) {
		return recipeRepository.findById(id);
	}

	@Override
	public <S extends Recipe> S save(S recipe) {
		//System.out.println("\nSAVING RECIPEEEEEE\n");
		for(Ingredient ingredient: recipe.getIngredients()) {
			Food f = ingredient.getFood();
			f.addIngredient(ingredient);
			foodRepository.save(f);
			
			Measure m = ingredient.getMeasure();
			m.addIngredient(ingredient);
			measureRepository.save(m);
			
		}
		
		/*for(DietLabel label : recipe.getDietLabels()) {
		}*/
		
		return recipeRepository.save(recipe);
	}

	@Override
	public <S extends Recipe> Iterable<S> saveAll(Iterable<S> recipes) {
		return recipeRepository.saveAll(recipes);
	}
}
