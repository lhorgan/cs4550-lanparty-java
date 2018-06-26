package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Recipe;
import webdev.models.Review;
import webdev.models.User;
import webdev.repositories.RecipeRepository;
import webdev.repositories.ReviewRepository;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;
    	
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
    
    @GetMapping("/api/review/id/{recipeId}")
    public List<Review> findReviewsForRecipeById(@PathVariable("recipeId") int recipeId) {
    	Optional<Recipe> data = recipeRepository.findById(recipeId);
    	if(data.isPresent()) {
    		Recipe recipe = data.get();
    		return recipe.getReviews();
    	}
    	return null;

    }
    
    @GetMapping("/api/review/uri/")
    public List<Review> findReviewsForRecipeByUri(@RequestParam("uri") String recipeUri) {
    	Optional<Recipe> data = recipeRepository.findRecipeByURI(recipeUri);
    	if(data.isPresent()) {
    		Recipe recipe = data.get();
    		return recipe.getReviews();
    	}
    	System.out.println("couldn't find recipe " + recipeUri);
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

    /*@PostMapping("/api/user/{uid}/review")
    public Review createReview(@PathVariable("uid") int userId, @RequestBody Review review, HttpSession httpSession) {
        Optional<User> maybeUser = userRepository.findById(userId);
        User sessionUser = (User) httpSession.getAttribute("user");

        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            if (user.getId() == sessionUser.getId()) {
                List<Review> reviews = user.getReviews();
                reviews.add(review);
                review.setUser(user);
                user.setReviews(reviews);
                userRepository.save(user);
                reviewRepository.save(review);
                return review;
            }
        }
        return null;
    }*/
    
    @PostMapping("/api/review/id/{recipeId}")
    public Review createReview(@PathVariable("recipeId") int recipeId, @RequestBody Review review, HttpSession httpSession) {
    	Optional<Recipe> data = recipeRepository.findById(recipeId);
    	User user = (User) httpSession.getAttribute("user");
    	if(data.isPresent() && user != null) {
    		Recipe recipe = data.get();
    		review.setRecipe(recipe);
    		user.setReputable(true);
    		httpSession.setAttribute("user", user); // update the user to be reputable
    		userRepository.save(user); // give user some street cred
    		return reviewRepository.save(review);
    	}
    	System.out.println("error, are you logged in?");
    	return null;
    }
    
    @DeleteMapping("/api/review/{rid}")
    public void deleteReview(@PathVariable("rid") int reviewId, HttpSession httpSession) {
        User sessionUser = (User) httpSession.getAttribute("user");
        Optional<Review> maybeReview = reviewRepository.findById(reviewId);

        if (maybeReview.isPresent()) {
            Review review = maybeReview.get();
            if (review.getUser().getId() == sessionUser.getId()) {
                reviewRepository.deleteById(reviewId);
            }
        }
    }
}
