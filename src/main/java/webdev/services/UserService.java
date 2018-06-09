package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import webdev.models.Review;
import webdev.models.User;
import webdev.repositories.ReviewRepository;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @PostMapping("/api/user")
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/api/user")
    public List<User> findAllUsers(@RequestParam(name="username", required = false) String username) {
        if (username != null) {
            return (List<User>) userRepository.findUserByUsername(username);
        }
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/user/{userId}")
    public User findUserById(@PathVariable("userId") int userId) {
        Optional<User> potentialUser = userRepository.findById(userId);
        if (potentialUser.isPresent()) {
            return potentialUser.get();
        }
        return null;
    }

    @PutMapping("/api/user/{userId}")
    public User updateUser(@RequestBody User newUser, @PathVariable("userId") int userId) {
        Optional<User> potentialUser = userRepository.findById(userId);
        if (potentialUser.isPresent()) {
            User user = potentialUser.get();
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setEmail(newUser.getEmail());
            user.setPhone(newUser.getPhone());
            user.setRole(newUser.getRole());
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setCreatedRecipes(newUser.getCreatedRecipes());
            user.setSavedRecipes(newUser.getSavedRecipes());
            user.setReviews(newUser.getReviews());
            user.setAdmin(newUser.isAdmin());
            user.setHasReputation(newUser.isHasReputation());
            user.setChef(newUser.isChef());
            user.setFollowing(newUser.getFollowing());
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {
        Optional<User> maybeUser = userRepository.findById(userId);
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();
            for (Review review : user.getReviews()) {
                reviewRepository.delete(review);
            }
        }
        userRepository.deleteById(userId);
    }

    @PostMapping("/api/user/{userId}/follow/{followUserId}")
    public User followUser(@PathVariable("userId") int userId, @PathVariable("followingUserId") int followUserId) {
        Optional<User> maybeUser = userRepository.findById(userId);
        Optional<User> maybeFollowUser = userRepository.findById(followUserId);

        if (maybeUser.isPresent() && maybeFollowUser.isPresent()) {
            User user = maybeUser.get();
            User followUser = maybeFollowUser.get();

            List<User> following = user.getFollowing();
            following.add(followUser);
            user.setFollowing(following);
            userRepository.save(user);
            return followUser;
        }
        return null;
    }

    @PostMapping("/api/register")
    public User register(@RequestBody User user, HttpSession session) {
        List<User> newUser = (List<User>) userRepository.findUserByUsername(user.getUsername());
        if (newUser.size() == 0) {
            userRepository.save(user);
            session.setAttribute("user", user);
            return user;
        }
        return null;
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();

        List<User> potentialuser = (List<User>) userRepository.findUserByUsernameAndPassword(username, password);

        if (potentialuser.size() != 0) {
            session.setAttribute("user", potentialuser.get(0));
            return potentialuser.get(0);
        }
        return null;
    }

    @PostMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
        return;
    }
}
