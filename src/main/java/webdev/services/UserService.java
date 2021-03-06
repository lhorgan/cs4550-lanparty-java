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
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
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
        List<User> users = (List<User>) userRepository.findAll();
        /*List<User> active = new ArrayList<User>();
        for(int i = 0; i < users.size(); i++) {
        	if(!users.get(i).isDeactivated()) {
        		active.add(users.get(i));
        	}
        }
        return active;*/
        return users;
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
    public User updateUser(@RequestBody User newUser, @PathVariable("userId") int userId, HttpSession session) {
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
            //user.setSavedRecipes(newUser.getSavedRecipes());
            //user.setReviews(newUser.getReviews());
            user.setAdmin(newUser.isAdmin());
            user.setReputable(newUser.isReputable());
            user.setChef(newUser.isChef());
            //user.setFollowings(newUser.getFollowings());
            user = userRepository.save(user);
            
            User sessionUser = (User) session.getAttribute("user");
            if(sessionUser != null && sessionUser.getId() == user.getId()) {
            	session.setAttribute("user", user);
            }
            
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

    @PutMapping("/api/user/profile")
    public User updateProfile(@RequestBody User user, HttpSession httpSession) {
        User sessionUser = (User) httpSession.getAttribute("user");
        if (sessionUser.getId() == user.getId()) {
            // whatever fields we wanna set here

            userRepository.save(sessionUser);
            return sessionUser;
        }
        return null;
    }

    @PutMapping("/api/user/follow/{userToFollowId}")
    public User followUser(@PathVariable("userToFollowId") int userToFollowId,
                           HttpSession httpSession) {
        User sessionUser = (User) httpSession.getAttribute("user");
        Optional<User> data = userRepository.findById(userToFollowId);

        if (data.isPresent() && sessionUser != null) {
            User userToFollow = data.get();

            sessionUser.follow(userToFollow);
            httpSession.setAttribute("user", sessionUser);
            return userRepository.save(sessionUser);
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
            System.out.println("Found user, setting session!");
            return potentialuser.get(0);
        }
        return null;
    }
    
    @GetMapping("/api/user/current")
    public User getLoggedInUser(HttpSession session) {
    	User sessionUser = (User) session.getAttribute("user");
    	if(sessionUser != null) {
	    	Optional<User> userFromDBData = userRepository.findById(sessionUser.getId());
	    	User user = null;
	    	if(userFromDBData.isPresent()) {
	    		user = userFromDBData.get();
	    	}
	    	session.setAttribute("user", user);
	    	
	    	if(user != null) {
	    		System.out.println("User is not null!");
	    	}
	    	else {
	    		System.out.println("Damn, the user is null");
	    	}
	    	return user;
    	}
    	return null;
    }
    
    /*@GetMapping("/api/user/following/list")
    public List<User> getFollowing(HttpSession session) {
    	//System.out.println("THIS IS THE FUNCTION WE ARE IN");
		User loggedIn = (User) session.getAttribute("user");
		if(loggedIn != null) {
			System.out.println("LENGTH OF FOLLOWING LIST: " + loggedIn.getFollowings().size());
			if(loggedIn.getFollowings() != null) {
				List<User> followings = loggedIn.getFollowings();
				for(int i = 0; i < followings.size(); i++) {
					User u = followings.get(i);
					u.setCreatedRecipes(null);
					u.setSavedRecipes(null);
					u.setEndorsedRecipes(null);
					u.setReviews(null);
					followings.set(i, u);
				}
				return followings;
			}
		}
		return null;
    }*/
    
	@GetMapping("/api/user/{uid}/following/list")
	public List<User> getFollowings(@PathVariable("uid") int uid) {
	  Optional<User> data = userRepository.findById(uid);
	    if(data.isPresent()) {
	      User user = data.get();
	      System.out.println("LENGTH OF FOLLOWING LIST: " + user.getFollowings().size());
	      if(user.getFollowings() != null) {
	        List<User> followings = user.getFollowings();
	        for(int i = 0; i < followings.size(); i++) {
	          User u = followings.get(i);
	          u.setCreatedRecipes(null);
	          u.setSavedRecipes(null);
	          u.setEndorsedRecipes(null);
	          u.setReviews(null);
	          followings.set(i, u);
	        }
	        return followings;
	      }
	    }
	    return null;
	}
    
    @GetMapping("/api/user/{uid}/followers/list")
    public List<User> getFollowers(@PathVariable("uid") int uid) {
    	Optional<User> data = userRepository.findById(uid);
		if(data.isPresent()) {
			User user = data.get();
			System.out.println("LENGTH OF FOLLOWING LIST: " + user.getFollowers().size());
			if(user.getFollowers() != null) {
				List<User> followers = user.getFollowers();
				for(int i = 0; i < followers.size(); i++) {
					User u = followers.get(i);
					u.setCreatedRecipes(null);
					u.setSavedRecipes(null);
					u.setEndorsedRecipes(null);
					u.setReviews(null);
					followers.set(i, u);
				}
				return followers;
			}
		}
		return null;
    }

    @GetMapping("/api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
        return;
    }
}
