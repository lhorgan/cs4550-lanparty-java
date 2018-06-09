package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import webdev.models.User;
import webdev.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@RestController
public class UserService {
    @Autowired
    UserRepository userRepository;

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
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") int userId) {
        userRepository.deleteById(userId);
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

}
