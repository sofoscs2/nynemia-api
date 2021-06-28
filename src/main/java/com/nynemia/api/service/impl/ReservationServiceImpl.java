package com.nynemia.api.service.impl;

import com.nynemia.api.model.Reservation;
import com.nynemia.api.model.Spot;
import com.nynemia.api.repository.ReservationRepository;
import com.nynemia.api.repository.SpotRepository;
import com.nynemia.api.service.ReservationService;
import com.nynemia.api.web.exception.ReservationNotFoundException;
import com.nynemia.api.web.exception.SpotNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsBySpotId(long spotId) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(SpotNotFoundException::new);

        List<Reservation> spotReservations = reservationRepository.findBySpot(spot);
        return spotReservations;
    }

    @Override
    public Optional<Reservation> getReservationById(long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(long id) {
        reservationRepository.findById(id)
                .orElseThrow(ReservationNotFoundException::new);

        reservationRepository.deleteById(id);
    }
}
