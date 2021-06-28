package com.nynemia.api.service;


import com.nynemia.api.model.Boat;
import com.nynemia.api.model.Reservation;
import com.nynemia.api.model.Review;
import com.nynemia.api.model.Skipper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SkipperService {

    public List<Skipper> getSkippers();
    public Optional<Skipper> getSkipperById(long id);
    public Skipper saveSkipper(Skipper skipper);
    public void deleteSkipper(long id);

    public List<Boat> getBoats(long skipperId);
    public Optional<Boat> getSkippersBoatById(long skipperId, long boatId);
    public void addBoat(long skipperId, Boat boat);
    public void removeBoat(long skipperId, long boatId);

    public List<Reservation> getReservations(long skipperId);
    public Optional<Reservation> getSkippersReservationById(long skipperId, long reservationId);
    public void addReservation(long skipperId, long boatId, long spotId, Reservation reservation);
    public void removeReservation(long skipperId, long reservationId);

    public Optional<Review> getReview(long skipperId, long marinaId);
    public void addReview(long skipperId, long marinaId, Review review);
    public void removeReview(long skipperId, long marinaId);
}
