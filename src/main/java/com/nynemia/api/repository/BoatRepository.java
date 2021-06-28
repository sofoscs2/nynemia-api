package com.nynemia.api.repository;

import com.nynemia.api.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat, Long> {
}
