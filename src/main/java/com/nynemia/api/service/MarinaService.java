package com.nynemia.api.service;

import com.nynemia.api.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MarinaService {

    public List<Marina> getMarinas();
    public Optional<Marina> getMarinaById(long id);
    public Marina saveMarina(Marina marina);
    public void deleteMarina(long id);

    public Marina getMarinaBySpotId(long spotId);

    public String addMarinasImage(long marinaId, MultipartFile image);
    public void removeMarinasImage(long marinaId, String imagePath);

    public List<PriceList> getPriceLists(long marinaId);
    public Optional<PriceList> getMarinasPriceListById(long marinaId, long priceListId);
    public void addPriceList(long marinaId, PriceList priceList);
    public void removePriceList(long marinaId, long priceListId);

    public List<Amenity> getAmenities(long marinaId);
    public Optional<Amenity> getMarinasAmenityById(long marinaId, long amenityId);
    public void addAmenity(long marinaId, long amenityId);
    public void removeAmenity(long marinaId, long amenityId);

    public Set<Spot> getSpots(long marinaId);
    public Optional<Spot> getMarinasSpotById(long marinaId, long spotId);
    public void addSpot(long marinaId, Spot spot);
    public void removeSpot(long marinaId, long spotId);

    public Set<Reservation> getReservations(long marinaId);
    public Optional<Reservation> getMarinasReservationById(long marinaId, long reservationId);

    public Set<Review> getReviews(long marinaId);
    public Optional<Review> getMarinasReviewById(ReviewId reviewId);
}
