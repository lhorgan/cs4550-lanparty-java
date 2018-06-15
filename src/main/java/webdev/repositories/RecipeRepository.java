package webdev.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import webdev.models.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
