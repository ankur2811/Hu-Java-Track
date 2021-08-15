package com.example.Assignment2.Models;

import javax.persistence.*;

@Entity
@Table
public class ReviewMapping {

    @Id
    @SequenceGenerator(
            name="review_sequence",
            sequenceName = "review_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "review_sequence"
    )

    private Long id;
    private Long userId;
    private Long courseId;
    private String review;
    private int rating;

    public ReviewMapping() {
    }

    public ReviewMapping(Long id, Long userId, Long courseId, String review, int rating) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.review = review;
        this.rating = rating;
    }

    public ReviewMapping(Long userId, Long courseId, String review, int rating) {
        this.userId = userId;
        this.courseId = courseId;
        this.review = review;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ReviewMapping{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
