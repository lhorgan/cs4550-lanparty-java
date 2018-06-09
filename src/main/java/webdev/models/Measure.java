package webdev.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Measure {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String label;

    public Measure(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Measure() {

    }

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
}
