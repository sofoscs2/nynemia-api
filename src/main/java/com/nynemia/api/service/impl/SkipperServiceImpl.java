package com.nynemia.api.service.impl;

import com.nynemia.api.model.*;
import com.nynemia.api.repository.*;
import com.nynemia.api.service.SkipperService;
import com.nynemia.api.web.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class  SkipperServiceImpl implements SkipperService {

    @Autowired
    private SkipperRepository skipperRepository;

    @Autowired
    private BoatRepository boatRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MarinaRepository marinaRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Skipper> getSkippers() {
        return skipperRepository.findAll();
    }

    @Override
    public Optional<Skipper> getSkipperById(long id) {
        return skipperRepository.findById(id);
    }

    @Override
    public Skipper saveSkipper(Skipper skipper) {
        return skipperRepository.save(skipper);
    }

    @Override
    public void deleteSkipper(long id) {
        skipperRepository.findById(id)
                .orElseThrow(SkipperNotFoundException::new);

        skipperRepository.deleteById(id);
    }

    @Override
    public List<Boat> getBoats(long skipperId) {
        Skipper skipper = skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        return skipper.getBoats();
    }

    @Override
    public Optional<Boat> getSkippersBoatById(long skipperId, long boatId) {
        Skipper skipper = skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        Boat boat = boatRepository.findById(boatId)
                .orElseThrow(BoatNotFoundException::new);

        if (skipper.getBoats().contains(boat)) {
            return Optional.ofNullable(boat);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void addBoat(long skipperId, Boat boat) {
        Skipper skipper = skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        skipper.addBoat(boat);
        boatRepository.save(boat);
        skipperRepository.save(skipper);
    }

    @Override
    public void removeBoat(long skipperId, long boatId) {
        Skipper skipper = skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        Boat boat = boatRepository.findById(boatId)
                .orElseThrow(BoatNotFoundException::new);

        skipper.removeBoat(boat);
        boatRepository.delete(boat);
        skipperRepository.save(skipper);
    }

    @Override
    public List<Reservation> getReservations(long skipperId) {
        List<Reservation> reservations = new ArrayList<>();

        List<Boat> boats = this.getBoats(skipperId);
        for (Boat boat : boats) {
            for (Reservation reservation: boat.getReservations()) {
                reservations.add(reservation);
            }
        }

        return reservations;
    }

    @Override
    public Optional<Reservation> getSkippersReservationById(long skipperId, long reservationId) {
        skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        List<Boat> boats = this.getBoats(skipperId);
        for (Boat boat : boats) {
            if (boat.getReservations().contains(reservation)) {
                return Optional.ofNullable(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addReservation(long skipperId, long boatId, long spotId, Reservation reservation) {
        Skipper skipper = skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        Boat boat = boatRepository.findById(boatId)
                .orElseThrow(BoatNotFoundException::new);

        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(SpotNotFoundException::new);

        if (skipper.getBoats().contains(boat)) {
            boat.addReservation(reservation);
            spot.addReservation(reservation);
            reservationRepository.save(reservation);
            boatRepository.save(boat);
            spotRepository.save(spot);
        }
    }

    @Override
    public void removeReservation(long skipperId, long reservationId) {
        Skipper skipper = skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        Boat boat = reservation.getBoat();
        Spot spot = reservation.getSpot();

        if (skipper.getBoats().contains(boat)) {
            boat.removeReservation(reservation);
            spot.removeReservation(reservation);
            reservationRepository.delete(reservation);
            boatRepository.save(boat);
            spotRepository.save(spot);
        }
    }

    @Override
    public Optional<Review> getReview(long skipperId, long marinaId) {
        return reviewRepository.findById(new ReviewId(skipperId, marinaId));
    }

    @Override
    public void addReview(long skipperId, long marinaId, Review review) {
        Skipper skipper = skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        skipper.addReview(review);
        marina.addReview(review);
        reviewRepository.save(review);
        skipperRepository.save(skipper);
        marinaRepository.save(marina);
    }

    @Override
    public void removeReview(long skipperId, long marinaId) {
        Skipper skipper = skipperRepository.findById(skipperId)
                .orElseThrow(SkipperNotFoundException::new);

        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        reviewRepository.deleteById(new ReviewId(skipperId, marinaId));
        skipperRepository.save(skipper);
        marinaRepository.save(marina);
    }
}
