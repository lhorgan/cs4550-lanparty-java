package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.DietLabelEntry;
import webdev.models.Food;

public interface DietLabelRepository extends CrudRepository<DietLabelEntry, Integer> {	
}