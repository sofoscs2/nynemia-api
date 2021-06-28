package com.nynemia.api.web;


import com.nynemia.api.model.*;
import com.nynemia.api.service.BoatService;
import com.nynemia.api.service.ReservationService;
import com.nynemia.api.service.ReviewService;
import com.nynemia.api.service.SkipperService;
import com.nynemia.api.web.exception.BoatNotFoundException;
import com.nynemia.api.web.exception.ReservationNotFoundException;
import com.nynemia.api.web.exception.SkipperNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SkipperController {

    @Autowired
    private SkipperService skipperService;

    @Autowired
    private BoatService boatService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/skippers")
    public List<Skipper> getSkippers() {
        return skipperService.getSkippers();
    }

    @GetMapping("/skippers/{id}")
    public Optional<Skipper> getSkipperById(@PathVariable long id) {
        return skipperService.getSkipperById(id);
    }

    @PostMapping("/skippers")
    @ResponseStatus(HttpStatus.CREATED)
    public Skipper createSkipper(@RequestBody Skipper skipper) {
        return skipperService.saveSkipper(skipper);
    }

    @PutMapping("/skippers/{id}")
    public Skipper updateSkipper(@RequestBody Skipper skipperRequest, @PathVariable long id) {
        return skipperService.getSkipperById(id).map(skipper -> {
            skipper.setFirstName(skipperRequest.getFirstName());
            skipper.setLastName(skipperRequest.getLastName());
            skipper.setBirthday(skipperRequest.getBirthday());
            skipper.setEmail(skipperRequest.getEmail());
            skipper.setPhoneNumber(skipperRequest.getPhoneNumber());
            skipper.setProfileImage(skipperRequest.getProfileImage());
            return skipperService.saveSkipper(skipper);
        }).orElseThrow(SkipperNotFoundException::new);
    }

    @DeleteMapping("/skippers/{id}")
    public void deleteSkipper(@PathVariable long id) {
        skipperService.deleteSkipper(id);
    }

    @GetMapping("/skippers/{skipperId}/boats")
    public List<Boat> getBoatsBySkipper(@PathVariable long skipperId) {
        return skipperService.getBoats(skipperId);
    }

    @PostMapping("/skippers/{skipperId}/boats")
    public void addBoatToSkipper(@PathVariable long skipperId, @RequestBody Boat boat) {
        skipperService.addBoat(skipperId, boat);
    }

    @PutMapping("/skippers/{skipperId}/boats/{boatId}")
    public Boat updateSkippersBoat(@PathVariable long skipperId, @PathVariable long boatId, @RequestBody Boat boatRequest) {
        return skipperService.getSkippersBoatById(skipperId, boatId).map(boat -> {
            boat.setName(boatRequest.getName());
            boat.setCategory(boatRequest.getCategory());
            boat.setLength(boatRequest.getLength());
            boat.setBeam(boatRequest.getBeam());
            boat.setDraft(boatRequest.getDraft());
            boat.setFlag(boatRequest.getFlag());
            boat.setPortRegistration(boatRequest.getPortRegistration());
            boat.setExpirationDateInsurance(boatRequest.getExpirationDateInsurance());
            return boatService.saveBoat(boat);
        }).orElseThrow(BoatNotFoundException::new);
    }

    @DeleteMapping("/skippers/{skipperId}/boats/{boatId}")
    public void removeBoatFromSkipper(@PathVariable long skipperId, @PathVariable long boatId) {
        skipperService.removeBoat(skipperId, boatId);
    }

    @GetMapping("/skippers/{skipperId}/reservations")
    public List<Reservation> getReservationsBySkipper(@PathVariable long skipperId) {
        return skipperService.getReservations(skipperId);
    }

    @GetMapping("/skippers/{skipperId}/reservations/{reservationId}")
    public Optional<Reservation> getSkippersReservationById(@PathVariable long skipperId, @PathVariable long reservationId) {
        return skipperService.getSkippersReservationById(skipperId, reservationId);
    }

    @PostMapping("/skippers/{skipperId}/boats/{boatId}/spots/{spotId}/reservations")
    public void addReservationToSkipper(@PathVariable long skipperId, @PathVariable long boatId,
                                        @PathVariable long spotId, @RequestBody Reservation reservation) {
        skipperService.addReservation(skipperId, boatId, spotId, reservation);
    }

    @PutMapping("/skippers/{skipperId}/reservations/{reservationId}")
    public Reservation updateSkippersReservation(@PathVariable long skipperId, @PathVariable long reservationId,
                                                 @RequestBody Reservation reservationRequest) {
        return skipperService.getSkippersReservationById(skipperId, reservationId).map(reservation -> {
            reservation.setArriveDate(reservationRequest.getArriveDate());
            reservation.setDepartureDate(reservationRequest.getDepartureDate());
            reservation.setStatus(reservationRequest.getStatus());
            return reservationService.saveReservation(reservation);
        }).orElseThrow(ReservationNotFoundException::new);
    }

    @DeleteMapping("/skippers/{skipperId}/reservations/{reservationId}")
    public void removerReservationFromSkipper(@PathVariable long skipperId, @PathVariable long reservationId) {
        skipperService.removeReservation(skipperId, reservationId);
    }

    @GetMapping("/skippers/{skipperId}/marinas/{marinaId}/reviews")
    public Optional<Review> getSkippersReview(@PathVariable long skipperId, @PathVariable long marinaId) {
        return skipperService.getReview(skipperId, marinaId);
    }

    @PostMapping("/skippers/{skipperId}/marinas/{marinaId}/reviews")
    public void addReviewToSkipper(@PathVariable long skipperId, @PathVariable long marinaId, @RequestBody Review review) {
        skipperService.addReview(skipperId, marinaId, review);
    }

    @PutMapping("/skippers/{skipperId}/marinas/{marinaId}/reviews")
    public Review updateSkippersReview(@PathVariable long skipperId, @PathVariable long marinaId, @RequestBody Review reviewRequest) {
        return reviewService.getReviewById(new ReviewId(skipperId, marinaId)).map(review -> {
            review.setStars(reviewRequest.getStars());
            review.setComment(reviewRequest.getComment());
            return reviewService.saveReview(review);
        }).orElseThrow(ReservationNotFoundException::new);
    }

    @DeleteMapping("/skippers/{skipperId}/marinas/{marinaId}/reviews")
    public void removeReviewFromSkipper(@PathVariable long skipperId, @PathVariable long marinaId) {
        skipperService.removeReview(skipperId, marinaId);
    }
}

