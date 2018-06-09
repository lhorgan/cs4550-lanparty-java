package webdev.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Ingredient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private float quantity;
    private Measure measure;
    private float weight;
    private Food food;

    public Ingredient(int id, float quantity, Measure measure, float weight, Food food) {
        this.id = id;
        this.quantity = quantity;
        this.measure = measure;
        this.weight = weight;
        this.food = food;
    }

    public Ingredient() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
