package webdev.repositories;

import org.springframework.data.repository.CrudRepository;
import webdev.models.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
