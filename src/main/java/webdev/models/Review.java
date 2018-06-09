package webdev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="recipe")
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int reviewId;
    private String review;

    public Review(int id, int userId, int reviewId, String review) {
        this.id = id;
        this.userId = userId;
        this.reviewId = reviewId;
        this.review = review;
    }

    public Review(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
