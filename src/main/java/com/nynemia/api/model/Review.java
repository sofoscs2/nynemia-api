package com.nynemia.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "review")
@IdClass(ReviewId.class)
public class Review {

    @Id
    @ManyToOne
    @JoinColumn(name = "skipper_id")
    private Skipper skipper;

    @Id
    @ManyToOne
    @JoinColumn(name = "marina_id")
    @JsonIgnore
    private Marina marina;

    private int stars;

    private String comment;

    public Review() {
    }

    public Review(int stars, String comment) {
        this.stars = stars;
        this.comment = comment;
    }

    public Skipper getSkipper() {
        return skipper;
    }

    public void setSkipper(Skipper skipper) {
        this.skipper = skipper;
    }

    public Marina getMarina() {
        return marina;
    }

    public void setMarina(Marina marina) {
        this.marina = marina;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
