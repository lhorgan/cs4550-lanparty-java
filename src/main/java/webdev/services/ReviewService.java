package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import webdev.models.Review;
import webdev.models.User;
import webdev.repositories.ReviewRepository;
import webdev.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/api/review/{reviewId}")
    public Review findReviewById(@PathVariable("reviewId") int reviewId) {
        Optional<Review> maybeReview = reviewRepository.findById(reviewId);
        if (maybeReview.isPresent()) {
            return maybeReview.get();
        }
        return null;
    }

    @GetMapping("/api/user/{uid}/review")
    public List<Review> findReviewsByUser(@PathVariable("uid") int userId) {
        Optional<User> maybeUser = userRepository.findById(userId);
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            return user.getReviews();
        }
        return null;
    }
}
