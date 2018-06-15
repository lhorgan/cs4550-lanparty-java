package webdev.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Embeddable
public class FoodId implements Serializable {
	private int id;
	private String label;
	
	public FoodId() {
		 
	}
 
	public FoodId(int id, String label) {
		this.id = id;
		this.label = label;
	}
 
	public int getId() {
		return this.id;
	}
 
	public String getLabel() {
		return this.label;
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((label == null) ? 0 : label.hashCode());
		result = prime * result + id;
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodId other = (FoodId) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
}
