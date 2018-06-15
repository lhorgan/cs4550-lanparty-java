package webdev.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webdev.models.Food;
import webdev.models.User;

@Repository("foodRepository")
public interface FoodRepository extends CrudRepository<Food, Integer> {	
	@Query(value = "SELECT f FROM food f WHERE f.label=:label", nativeQuery=true)
    Optional<Food> findFoodByLabel(@Param("label") String string);
}