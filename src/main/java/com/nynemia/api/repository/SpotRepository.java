package com.nynemia.api.repository;

import com.nynemia.api.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}
