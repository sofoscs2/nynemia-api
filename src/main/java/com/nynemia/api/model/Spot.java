package com.nynemia.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long spotId;

    private String name;

    private double length;

    private double beam;

    private double draft;

    @ManyToOne
    @JoinColumn(name = "marina_id")
    @JsonIgnore
    private Marina marina;

    @OneToMany(mappedBy = "spot")
    @JsonIgnore
    private Set<Reservation> reservations;

    public Spot() {
    }

    public Spot(String name, double length, double beam, double draft) {
        this.name = name;
        this.length = length;
        this.beam = beam;
        this.draft = draft;
    }

    public long getSpotId() {
        return spotId;
    }

    public void setSpotId(long spotId) {
        this.spotId = spotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBeam() {
        return beam;
    }

    public void setBeam(double beam) {
        this.beam = beam;
    }

    public double getDraft() {
        return draft;
    }

    public void setDraft(double draft) {
        this.draft = draft;
    }

    public Marina getMarina() {
        return marina;
    }

    public void setMarina(Marina marina) {
        this.marina = marina;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        if (reservations == null) {
            reservations = new HashSet<>();
        }

        reservations.add(reservation);
        reservation.setSpot(this);
    }

    public void removeReservation(Reservation reservation) {
        if (reservations != null) {
            reservations.remove(reservation);
            reservation.setSpot(null);
        }
    }
}
