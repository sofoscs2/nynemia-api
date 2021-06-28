package com.nynemia.api.web;

import com.nynemia.api.model.*;
import com.nynemia.api.service.MarinaService;
import com.nynemia.api.service.PriceListService;
import com.nynemia.api.service.ReservationService;
import com.nynemia.api.service.SpotService;
import com.nynemia.api.web.exception.MarinaNotFoundException;
import com.nynemia.api.web.exception.PriceListNotFoundException;
import com.nynemia.api.web.exception.ReservationNotFoundException;
import com.nynemia.api.web.exception.SpotNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController()
@RequestMapping("/api")
public class MarinaController {

    @Autowired
    private MarinaService marinaService;

    @Autowired
    private PriceListService priceListService;

    @Autowired
    private SpotService spotService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/marinas")
    public List<Marina> getMarinas() {
        return marinaService.getMarinas();
    }

    @GetMapping("/marinas/{id}")
    public Optional<Marina> getMarinaById(@PathVariable long id) {
        return marinaService.getMarinaById(id);
    }

    @PostMapping("/marinas")
    @ResponseStatus(HttpStatus.CREATED)
    public Marina createMarina(@RequestBody Marina marina) {
        return marinaService.saveMarina(marina);
    }

    @PutMapping("/marinas/{id}")
    public Marina updateMarina(@RequestBody Marina marinaRequest, @PathVariable long id) {
        return marinaService.getMarinaById(id).map(marina -> {
            marina.setName(marinaRequest.getName());
            marina.setDescription(marinaRequest.getDescription());
            marina.setLocation(marinaRequest.getLocation());
            marina.setEmail(marinaRequest.getEmail());
            marina.setPhoneNumber(marinaRequest.getPhoneNumber());
            marina.setLocation(marinaRequest.getLocation());
            marina.setRadioSignal(marinaRequest.getRadioSignal());
            marina.setWebsiteLink(marinaRequest.getWebsiteLink());
            marina.setPassword(marinaRequest.getPassword());
            return marinaService.saveMarina(marina);
        }).orElseThrow(MarinaNotFoundException::new);
    }

    @DeleteMapping("/marinas/{id}")
    public void deleteMarina(@PathVariable long id) {
        marinaService.deleteMarina(id);
    }

    @GetMapping("/marinas/spot/{spotId}")
    public Marina getMarinaBySpot(@PathVariable long spotId) {
        return marinaService.getMarinaBySpotId(spotId);
    }

    @PostMapping("/marinas/{marinaId}/images")
    public String addImageToMarina(@PathVariable long marinaId, @RequestParam("file") MultipartFile file) {
        return marinaService.addMarinasImage(marinaId, file);
    }

    @GetMapping("/marinas/{marinaId}/price-lists")
    public List<PriceList> getPriceListsByMarina(@PathVariable long marinaId) {
        return marinaService.getPriceLists(marinaId);
    }

    @PostMapping("/marinas/{marinaId}/price-lists")
    public void addPriceListToMarina(@PathVariable long marinaId, @RequestBody PriceList priceList) {
        marinaService.addPriceList(marinaId, priceList);
    }

    @PutMapping("/marinas/{marinaId}/price-lists/{priceListId}")
    public PriceList updateMarinasPriceList(@PathVariable long marinaId, @PathVariable long priceListId, @RequestBody PriceList priceListRequest) {
        return marinaService.getMarinasPriceListById(marinaId, priceListId).map(priceList -> {
            priceList.setBoatLength(priceListRequest.getBoatLength());
            priceList.setAnnualPrice(priceListRequest.getAnnualPrice());
            return priceListService.savePriceList(priceList);
        }).orElseThrow(PriceListNotFoundException::new);
    }

    @DeleteMapping("/marinas/{marinaId}/price-lists/{priceListId}")
    public void removePriceListFromMarina(@PathVariable long marinaId, @PathVariable long priceListId) {
        marinaService.removePriceList(marinaId, priceListId);
    }

    @GetMapping("/marinas/{marinaId}/amenities")
    public List<Amenity> getAmenitiesByMarina(@PathVariable long marinaId) {
        return marinaService.getAmenities(marinaId);
    }

    @PostMapping("/marinas/{marinaId}/amenities/{amenityId}")
    public void addAmenityToMarina(@PathVariable long marinaId, @PathVariable long amenityId) {
        marinaService.addAmenity(marinaId, amenityId);
    }

    @DeleteMapping("/marinas/{marinaId}/amenities/{amenityId}")
    public void removeAmenityFromMarina(@PathVariable long marinaId, @PathVariable long amenityId) {
        marinaService.removeAmenity(marinaId, amenityId);
    }

    @GetMapping("/marinas/{marinaId}/spots")
    public Set<Spot> getSpots(@PathVariable long marinaId) {
        return marinaService.getSpots(marinaId);
    }

    @GetMapping("/marinas/{marinaId}/spots/{arriveDate}/{departureDate}")
    public Set<Spot> getAvailableSpots(@PathVariable long marinaId, @PathVariable String arriveDate, @PathVariable String departureDate) {
        Set<Spot> availableSpots = new HashSet<>();
        Set<Spot> marinaSpots = marinaService.getSpots(marinaId);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date arrive = null;
        Date departure = null;
        try {
            arrive = dateFormat.parse(arriveDate);
            departure = dateFormat.parse(departureDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Spot spot : marinaSpots) {
            List<Reservation> spotReservations = reservationService.getReservationsBySpotId(spot.getSpotId());
            boolean available = true;
            for (Reservation reservation : spotReservations) {
                Date reservationArrive = null;
                Date reservationDeparture = null;
                try {
                    reservationArrive = dateFormat.parse(reservation.getArriveDate());
                    reservationDeparture = dateFormat.parse(reservation.getDepartureDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if ((reservationArrive.before(arrive) && reservationDeparture.after(arrive)) ||
                    (reservationArrive.before(departure) && reservationDeparture.after(departure)) ||
                    (reservationArrive.after(arrive) && reservationDeparture.before(departure))) {
                    available = false;
                    break;
                }
            }
            if (available) availableSpots.add(spot);
        }
        return availableSpots;
    }

    @GetMapping("/marinas/{marinaId}/spots/{spotId}")
    public Optional<Spot> getMarinasSpotById(@PathVariable long marinaId, @PathVariable long spotId) {
        return marinaService.getMarinasSpotById(marinaId, spotId);
    }

    @PostMapping("/marinas/{marinaId}/spots")
    public void addSpotToMarina(@PathVariable long marinaId, @RequestBody Spot spot) {
        marinaService.addSpot(marinaId, spot);
    }

    @PutMapping("/marinas/{marinaId}/spots/{spotId}")
    public Spot updateMarinasSpot(@PathVariable long marinaId, @PathVariable long spotId, @RequestBody Spot spotRequest) {
        return marinaService.getMarinasSpotById(marinaId, spotId).map(spot -> {
            spot.setName(spotRequest.getName());
            spot.setLength(spotRequest.getLength());
            spot.setBeam(spotRequest.getBeam());
            spot.setDraft(spotRequest.getDraft());
            return spotService.saveSpot(spot);
        }).orElseThrow(SpotNotFoundException::new);
    }

    @DeleteMapping("/marinas/{marinaId}/spots/{spotId}")
    public void removeSpotFromMarina(@PathVariable long marinaId, @PathVariable long spotId) {
        marinaService.removeSpot(marinaId, spotId);
    }

    @GetMapping("/marinas/{marinaId}/reservations")
    public Set<Reservation> getReservations(@PathVariable long marinaId) {
        return marinaService.getReservations(marinaId);
    }

    @GetMapping("/marinas/{marinaId}/reservations/{reservationId}")
    public Optional<Reservation> getMarinasReservationById(@PathVariable long marinaId, @PathVariable long reservationId) {
        return marinaService.getMarinasReservationById(marinaId, reservationId);
    }

    @PutMapping("/marinas/{marinaId}/reservations/{reservationId}")
    public Reservation updateMarinasReservation(@PathVariable long marinaId, @PathVariable long reservationId,
                                                @RequestBody Reservation reservationRequest) {
        return marinaService.getMarinasReservationById(marinaId, reservationId).map(reservation -> {
            reservation.setStatus(reservationRequest.getStatus());
            return reservationService.saveReservation(reservation);
        }).orElseThrow(ReservationNotFoundException::new);
    }

    @GetMapping("/marinas/{marinaId}/reviews")
    public Set<Review> getReviews(@PathVariable long marinaId) {
        return marinaService.getReviews(marinaId);
    }

    @GetMapping("/marinas/{marinaId}/reviews/{skipperId}")
    public Optional<Review> getMarinasReviewsById(@PathVariable long marinaId, @PathVariable long skipperId) {
        return marinaService.getMarinasReviewById(new ReviewId(skipperId, marinaId));
    }
}
