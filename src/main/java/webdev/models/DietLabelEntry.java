package webdev.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import webdev.enumerations.DietLabel;

@Entity
public class DietLabelEntry {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	DietLabel label;
	
	@ManyToMany
    private List<Recipe> recipes;
	
	void setLabel(DietLabel label) {
		this.label = label;
	}
	DietLabel getLabel() {
		return this.label;
	}
	
	void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	List<Recipe> getRecipes() {
		return this.recipes;
	}
}
