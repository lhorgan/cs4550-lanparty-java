package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private boolean hasReputation;
    private boolean isChef;

//    @OneToMany(mappedBy="user")
//    @JsonIgnore
//    private List<Recipe> createdRecipes;
//    @ManyToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Recipe> savedRecipes;
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Review> reviews;
//    @ManyToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<User> following;

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

//    public List<Recipe> getCreatedRecipes() {
//        return createdRecipes;
//    }
//
//    public void setCreatedRecipes(List<Recipe> createdRecipes) {
//        this.createdRecipes = createdRecipes;
//    }
//
//    public List<Recipe> getSavedRecipes() {
//        return savedRecipes;
//    }
//
//    public void setSavedRecipes(List<Recipe> savedRecipes) {
//        this.savedRecipes = savedRecipes;
//    }
//
//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isHasReputation() {
        return hasReputation;
    }

    public void setHasReputation(boolean hasReputation) {
        this.hasReputation = hasReputation;
    }

    public boolean isChef() {
        return isChef;
    }

    public void setChef(boolean chef) {
        isChef = chef;
    }

//    public List<User> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(List<User> following) {
//        this.following = following;
//    }
}
