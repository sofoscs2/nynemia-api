package com.nynemia.api.repository;

import com.nynemia.api.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
}
