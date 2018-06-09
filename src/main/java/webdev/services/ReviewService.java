package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webdev.models.Review;
import webdev.repositories.ReviewRepository;
import webdev.repositories.UserRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/review")
    public List<Review> findAllReviews() {
        return (List<Review>) reviewRepository.findAll();
    }
}
