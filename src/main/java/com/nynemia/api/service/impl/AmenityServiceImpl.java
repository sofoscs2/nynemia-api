package com.nynemia.api.service.impl;

import com.nynemia.api.model.Amenity;
import com.nynemia.api.repository.AmenityRepository;
import com.nynemia.api.service.AmenityService;
import com.nynemia.api.web.exception.AmenityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public List<Amenity> getAmenities() {
        return amenityRepository.findAll();
    }

    @Override
    public Optional<Amenity> getAmenityById(long id) {
        return amenityRepository.findById(id);
    }

    @Override
    public Amenity saveAmenity(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    @Override
    public void deleteAmenity(long id) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(AmenityNotFoundException::new);

        amenityRepository.delete(amenity);
    }
}
