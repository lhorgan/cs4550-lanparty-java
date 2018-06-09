package webdev.models;

import webdev.enumerations.DietLabel;
import webdev.enumerations.HealthLabel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity(name="recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int numServings;
    private int calories;
    private List<HealthLabel> healthTags;
    private List<DietLabel> dietTags;
    private String imageUrl;
    private String instructions;

    public Recipe(int id, String name, String description, int numServings, int calories, List<HealthLabel> healthTags,
                  List<DietLabel> dietTags, String imageUrl, String instructions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numServings = numServings;
        this.calories = calories;
        this.healthTags = healthTags;
        this.dietTags = dietTags;
        this.imageUrl = imageUrl;
        this.instructions = instructions;
    }

    public Recipe() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumServings() {
        return numServings;
    }

    public void setNumServings(int numServings) {
        this.numServings = numServings;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public List<HealthLabel> getHealthTags() {
        return healthTags;
    }

    public void setHealthTags(List<HealthLabel> healthTags) {
        this.healthTags = healthTags;
    }

    public List<DietLabel> getDietTags() {
        return dietTags;
    }

    public void setDietTags(List<DietLabel> dietTags) {
        this.dietTags = dietTags;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
