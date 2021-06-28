package com.nynemia.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "skipper")
public class Skipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long skipperId;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    private String email;

    private String phoneNumber;

    private String profileImage;

    private String username;

    private String password;

    @OneToMany(mappedBy = "skipper")
    private List<Boat> boats;

    @OneToMany(mappedBy = "skipper")
    @JsonIgnore
    private Set<Review> reviews;

    public Skipper() {}

    public Skipper(String firstName, String lastName, Date birthday, String email, String phoneNumber, String profileImage, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.username = username;
        this.password = password;
    }

    public long getSkipperId() {
        return skipperId;
    }

    public void setSkipperId(long skipperId) {
        this.skipperId = skipperId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void addBoat(Boat boat) {
        if (boats == null) {
            boats = new ArrayList<>();
        }

        boats.add(boat);
        boat.setSkipper(this);
    }

    public void removeBoat(Boat boat) {
        if (boats != null) {
            boats.remove(boat);
            boat.setSkipper(null);
        }
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new HashSet<>();
        }

        reviews.add(review);
        review.setSkipper(this);
    }

    public void removeReview(Review review) {
        if (reviews != null) {
            reviews.remove(review);
            review.setSkipper(null);
        }
    }
}
