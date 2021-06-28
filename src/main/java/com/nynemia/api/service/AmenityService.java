package com.nynemia.api.service;

import com.nynemia.api.model.Amenity;

import java.util.List;
import java.util.Optional;

public interface AmenityService {

    public List<Amenity> getAmenities();
    public Optional<Amenity> getAmenityById(long id);
    public Amenity saveAmenity(Amenity amenity);
    public void deleteAmenity(long id);
}
