package com.nynemia.api.web;

import com.nynemia.api.model.Amenity;
import com.nynemia.api.service.AmenityService;
import com.nynemia.api.web.exception.AmenityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @GetMapping("/amenities")
    public List<Amenity> getAmenities() {
        return amenityService.getAmenities();
    }

    @GetMapping("/amenities/{id}")
    public Optional<Amenity> getAmenityById(@PathVariable long id) {
        return amenityService.getAmenityById(id);
    }

    @PostMapping("/amenities")
    @ResponseStatus(HttpStatus.CREATED)
    public Amenity createAmenity(@RequestBody Amenity amenity) {
        return amenityService.saveAmenity(amenity);
    }

    @PutMapping("/amenities/{id}")
    public Amenity updateAmenity(@RequestBody Amenity amenityRequest, @PathVariable long id) {
        return amenityService.getAmenityById(id).map(amenity -> {
            amenity.setName(amenityRequest.getName());
            amenity.setImage(amenityRequest.getImage());
            return amenityService.saveAmenity(amenity);
        }).orElseThrow(AmenityNotFoundException::new);
    }

    @DeleteMapping("/amenities/{id}")
    public void deleteAmenity(@PathVariable long id) {
        amenityService.deleteAmenity(id);
    }
}
