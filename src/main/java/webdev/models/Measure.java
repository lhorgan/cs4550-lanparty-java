package webdev.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Measure {
    @Id
    @Column(name="label", nullable=false)
    private String label;
    
    @OneToMany(mappedBy="food")
    @JsonIgnore
    private List<Ingredient> ingredients; // ingredients I belong to

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

	public void addIngredient(Ingredient ingredient) {
		if(this.ingredients == null) {
			this.ingredients = new ArrayList<Ingredient>();
		}
		this.ingredients.add(ingredient);
	}
}
