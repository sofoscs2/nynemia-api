package com.nynemia.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "marina")
public class Marina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long marinaId;

    private String name;

    private String description;

    private String location;

    private String email;

    private String phoneNumber;

    private String websiteLink;

    private String radioSignal;

    private String imagePath;

    private String password;

    @OneToMany(mappedBy = "marina")
    private List<PriceList> priceLists;

    @ManyToMany
    @JoinTable(name = "marina_amenity",
            joinColumns = @JoinColumn(name = "marina_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private List<Amenity> amenities;

    @OneToMany(mappedBy = "marina")
    private Set<Spot> spots;

    @OneToMany(mappedBy = "marina")
    private Set<Review> reviews;

    public Marina() {}

    public Marina(String name, String description, String location, String email, String phoneNumber, String websiteLink, String radioSignal, String password) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.websiteLink = websiteLink;
        this.radioSignal = radioSignal;
        this.password = password;
    }

    public long getMarinaId() {
        return marinaId;
    }

    public void setMarinaId(long marinaId) {
        this.marinaId = marinaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getRadioSignal() {
        return radioSignal;
    }

    public void setRadioSignal(String radioSignal) {
        this.radioSignal = radioSignal;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public Set<Spot> getSpots() {
        return spots;
    }

    public void setSpots(Set<Spot> spots) {
        this.spots = spots;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void addPriceList(PriceList priceList) {
        if (priceLists == null) {
            priceLists = new ArrayList<>();
        }

        priceLists.add(priceList);
        priceList.setMarina(this);
    }

    public void removePriceList(PriceList priceList) {
        if (priceLists != null) {
            priceLists.remove(priceList);
            priceList.setMarina(null);
        }
    }

    public void addAmenity(Amenity amenity) {
        if (amenities == null) {
            amenities = new ArrayList<>();
        }

        amenities.add(amenity);

        if (amenity.getMarinas() == null) {
            amenity.setMarinas(new HashSet<>());
        }
        amenity.getMarinas().add(this);
    }

    public void removeAmenity(Amenity amenity) {
        amenities.remove(amenity);
        amenity.getMarinas().remove(this);
    }

    public void addSpot(Spot spot) {
        if (spots == null) {
            spots = new HashSet<>();
        }

        spots.add(spot);
        spot.setMarina(this);
    }

    public void removeSpot(Spot spot) {
        if (spots != null) {
            spots.remove(spot);
            spot.setMarina(null);
        }
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new HashSet<>();
        }

        reviews.add(review);
        review.setMarina(this);
    }

    public void removeReview(Review review) {
        if (reviews != null) {
            reviews.remove(review);
            review.setMarina(null);
        }
    }
}
