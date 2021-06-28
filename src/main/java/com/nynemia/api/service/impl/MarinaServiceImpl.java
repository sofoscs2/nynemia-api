package com.nynemia.api.service.impl;

import com.nynemia.api.model.*;
import com.nynemia.api.repository.*;
import com.nynemia.api.service.MarinaService;
import com.nynemia.api.web.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MarinaServiceImpl implements MarinaService {

    @Autowired
    private MarinaRepository marinaRepository;

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CloudinaryServiceImpl cloudinaryService;

    @Override
    public List<Marina> getMarinas() {
        return marinaRepository.findAll();
    }

    @Override
    public Optional<Marina> getMarinaById(long id) {
        return marinaRepository.findById(id);
    }

    @Override
    public Marina saveMarina(Marina marina) {
        return marinaRepository.save(marina);
    }

    @Override
    public void deleteMarina(long id) {
        marinaRepository.findById(id)
                .orElseThrow(MarinaNotFoundException::new);

        marinaRepository.deleteById(id);
    }

    @Override
    public Marina getMarinaBySpotId(long spotId) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(SpotNotFoundException::new);

        return marinaRepository.findBySpots(spot);
    }

    @Override
    public String addMarinasImage(long marinaId, MultipartFile image) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        String path = cloudinaryService.uploadFile(image);
        marina.setImagePath(path);
        marinaRepository.save(marina);
        return marina.getImagePath();
    }

    @Override
    public void removeMarinasImage(long marinaId, String imagePath) {

    }

    @Override
    public List<PriceList> getPriceLists(long marinaId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);
        return marina.getPriceLists();
    }

    @Override
    public Optional<PriceList> getMarinasPriceListById(long marinaId, long priceListId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        PriceList priceList = priceListRepository.findById(priceListId)
                .orElseThrow(PriceListNotFoundException::new);

        if (marina.getPriceLists().contains(priceList)) {
            return Optional.ofNullable(priceList);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void addPriceList(long marinaId, PriceList priceList) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        marina.addPriceList(priceList);
        priceListRepository.save(priceList);
        marinaRepository.save(marina);
    }

    @Override
    public void removePriceList(long marinaId, long priceListId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        PriceList priceList = priceListRepository.findById(priceListId)
                .orElseThrow(PriceListNotFoundException::new);

        marina.removePriceList(priceList);
        priceListRepository.delete(priceList);
        marinaRepository.save(marina);
    }

    @Override
    public List<Amenity> getAmenities(long marinaId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);
        return marina.getAmenities();
    }

    @Override
    public Optional<Amenity> getMarinasAmenityById(long marinaId, long amenityId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(AmenityNotFoundException::new);

        if (marina.getAmenities().contains(amenity)) {
            return Optional.ofNullable(amenity);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void addAmenity(long marinaId, long amenityId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(AmenityNotFoundException::new);

        marina.addAmenity(amenity);
        amenityRepository.save(amenity);
        marinaRepository.save(marina);
    }

    @Override
    public void removeAmenity(long marinaId, long amenityId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(AmenityNotFoundException::new);

        marina.removeAmenity(amenity);
        amenityRepository.save(amenity);
        marinaRepository.save(marina);
    }

    @Override
    public Set<Spot> getSpots(long marinaId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        return marina.getSpots();
    }

    @Override
    public Optional<Spot> getMarinasSpotById(long marinaId, long spotId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(MarinaNotFoundException::new);

        if (marina.getSpots().contains(spot)) {
            return Optional.ofNullable(spot);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public void addSpot(long marinaId, Spot spot) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        marina.addSpot(spot);
        spotRepository.save(spot);
        marinaRepository.save(marina);
    }

    @Override
    public void removeSpot(long marinaId, long spotId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(SpotNotFoundException::new);

        marina.removeSpot(spot);
        spotRepository.delete(spot);
        marinaRepository.save(marina);
    }

    @Override
    public Set<Reservation> getReservations(long marinaId) {
        Set<Reservation> reservations = new HashSet<>();

        Set<Spot> spots = this.getSpots(marinaId);
        for (Spot spot : spots) {
            reservations.addAll(spot.getReservations());
        }

        return reservations;
    }

    @Override
    public Optional<Reservation> getMarinasReservationById(long marinaId, long reservationId) {
        marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(ReservationNotFoundException::new);

        Set<Spot> spots = this.getSpots(marinaId);
        for (Spot spot : spots) {
            if (spot.getReservations().contains(reservation)) {
                return Optional.ofNullable(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<Review> getReviews(long marinaId) {
        Marina marina = marinaRepository.findById(marinaId)
                .orElseThrow(MarinaNotFoundException::new);

        return marina.getReviews();
    }

    @Override
    public Optional<Review> getMarinasReviewById(ReviewId reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);

        return Optional.ofNullable(review);
    }
}
