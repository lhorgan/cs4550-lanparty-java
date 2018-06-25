package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String role;
    private Date dateOfBirth;
    private boolean isAdmin;
    private boolean isReputable;
    private boolean isChef;

    @OneToMany(mappedBy="createdByUser")
    private List<Recipe> createdRecipes = new ArrayList<Recipe>();
    
    @ManyToMany(
        cascade = {
            CascadeType.ALL
        }
    )
    @JoinTable(
        name = "user_saved_recipe",
        joinColumns = { @JoinColumn(name = "user_saved_id") },
        inverseJoinColumns = { @JoinColumn(name = "recipe_saved_id") }
    )
    private Set<Recipe> savedRecipes = new HashSet<Recipe>();
    
    @ManyToMany(
		cascade = {
	            CascadeType.ALL
	        }
    )
    @JoinTable(
        name = "user_endorsed_recipe",
        joinColumns = @JoinColumn(name = "user_endorsed_id"),
        inverseJoinColumns = @JoinColumn(name = "recipe_endorsed_id")
    )
    private Set<Recipe> endorsedRecipes = new HashSet<Recipe>();
    
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<Review>();
    
    /*@ManyToMany(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }
    )
    @JoinTable(
        name = "user_user",
        joinColumns = @JoinColumn(name = "user_id_a"),
        inverseJoinColumns = @JoinColumn(name = "user_id_b")
    )
    @JsonIgnore
    private List<User> following;*/
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tbl_following",
     joinColumns=@JoinColumn(name="followerId"),
     inverseJoinColumns=@JoinColumn(name="followingId")
    )
    @JsonIgnore
    private List<User> followings = new ArrayList<User>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tbl_following",
     joinColumns=@JoinColumn(name="followingId"),
     inverseJoinColumns=@JoinColumn(name="followerId")
    )
    @JsonIgnore
    private List<User> followers = new ArrayList<User>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(List<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }

    public Set<Recipe> getSavedRecipes() {
        return savedRecipes;
    }

    public void setSavedRecipes(Set<Recipe> savedRecipes) {
        this.savedRecipes = savedRecipes;
    }
    
    public void saveRecipe(Recipe recipe) {
    	savedRecipes.add(recipe);
    }
    
    public Set<Recipe> getEndorsedRecipes() {
        return endorsedRecipes;
    }

    public void setEndorsedRecipes(Set<Recipe> endorsedRecipes) {
        this.endorsedRecipes = endorsedRecipes;
    }
    
    public void endorseRecipe(Recipe recipe) {
    	endorsedRecipes.add(recipe);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isReputable() {
        return isReputable;
    }

    public void setReputable(boolean isReputable) {
        this.isReputable = isReputable;
    }

    public boolean isChef() {
        return isChef;
    }

    public void setChef(boolean chef) {
        isChef = chef;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
    
    public List<User> getFollowings() {
    	//Hibernate.initialize(this.createdRecipes);
        return followings;
    }

    public void setFollowings(List<User> following) {
        this.followings = following;
    }
    
    public void follow(User user) {
    	this.followings.add(user);
    }
}
