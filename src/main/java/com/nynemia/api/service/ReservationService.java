package com.nynemia.api.service;

import com.nynemia.api.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    public List<Reservation> getReservations();
    public List<Reservation> getReservationsBySpotId(long spotId);
    public Optional<Reservation> getReservationById(long id);
    public Reservation saveReservation(Reservation reservation);
    public void deleteReservation(long id);
}
