package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import webdev.enumerations.DietLabel;
import webdev.enumerations.HealthLabel;

import javax.persistence.*;
import java.util.List;

@Entity(name="recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String uri;
    private String label;
    private String image;
    private String url;
    private int yield;
    private float calories;
    private List<DietLabel> dietLabels;
    private List<HealthLabel> healthLabels;

    private String description;
    private String instructions;

    @ManyToMany(mappedBy="recipe")
    @JsonIgnore
    private List<Ingredient> ingredients;
    @ManyToMany(mappedBy="recipe")
    private List<NutrientInfo> totalNutrients;

    @ManyToOne()
    @JsonIgnore
    private User createdByUser;
    @ManyToMany(mappedBy = "recipe")
    @JsonIgnore
    private List<User> savedByUser;

    public Recipe(int id, String uri, String label, String image, String url, int yield, float calories,
                  List<DietLabel> dietLabels, List<HealthLabel> healthLabels, String description, String instructions,
                  List<Ingredient> ingredients, List<NutrientInfo> totalNutrients, User createdByUser,
                  List<User> savedByUser) {
        this.id = id;
        this.uri = uri;
        this.label = label;
        this.image = image;
        this.url = url;
        this.yield = yield;
        this.calories = calories;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.description = description;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.totalNutrients = totalNutrients;
        this.createdByUser = createdByUser;
        this.savedByUser = savedByUser;
    }

    public Recipe() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<NutrientInfo> getTotalNutrients() {
        return totalNutrients;
    }

    public void setTotalNutrients(List<NutrientInfo> totalNutrients) {
        this.totalNutrients = totalNutrients;
    }

    public List<DietLabel> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(List<DietLabel> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public List<HealthLabel> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(List<HealthLabel> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public List<User> getSavedByUser() {
        return savedByUser;
    }

    public void setSavedByUser(List<User> savedByUser) {
        this.savedByUser = savedByUser;
    }
}
