package com.nynemia.api.repository;

import com.nynemia.api.model.Marina;
import com.nynemia.api.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  MarinaRepository extends JpaRepository<Marina, Long> {
    Marina findBySpots(Spot spot);
}
