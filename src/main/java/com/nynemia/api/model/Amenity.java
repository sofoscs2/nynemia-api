package com.nynemia.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "amenity")
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long amenityId;

    private String name;

    private String image;

    @ManyToMany(mappedBy = "amenities")
    @JsonIgnore
    private Set<Marina> marinas;

    public Amenity() {
    }

    public Amenity(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public long getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(long amenityId) {
        this.amenityId = amenityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Marina> getMarinas() {
        return marinas;
    }

    public void setMarinas(Set<Marina> marinas) {
        this.marinas = marinas;
    }
}
