package webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name="recipe")
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int reviewId;
    private String review;
    @ManyToOne
    @JsonIgnore
    private User user;

    public Review(int id, int reviewId, String review, User user) {
        this.id = id;
        this.reviewId = reviewId;
        this.review = review;
        this.user = user;
    }

    public Review(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
