package webdev.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import webdev.models.Measure;

@Repository
public interface MeasureRepository extends CrudRepository<Measure, Integer> {	
	@Query(value = "SELECT label FROM measure m WHERE m.label=:label", nativeQuery=true)
    Iterable<Measure> findMeasureByLabel(@Param("label") String string);
}