package com.nynemia.api.repository;

import com.nynemia.api.model.Reservation;
import com.nynemia.api.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findBySpot(Spot spot);
}
