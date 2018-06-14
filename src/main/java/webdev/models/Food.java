package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String label;

    @OneToMany(mappedBy="food")
    @JsonIgnore
    private List<Ingredient> ingredients; // ingredients I belong to

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
