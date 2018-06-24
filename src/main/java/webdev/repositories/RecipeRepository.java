package webdev.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webdev.models.Recipe;
import webdev.models.User;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {	
	@Query("SELECT r FROM recipe r WHERE r.uri=:uri")
    Optional<Recipe> findRecipeByURI(@Param("uri") String u);
}
