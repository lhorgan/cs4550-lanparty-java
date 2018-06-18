package webdev.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import webdev.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
