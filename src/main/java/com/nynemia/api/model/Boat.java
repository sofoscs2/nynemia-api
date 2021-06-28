package com.nynemia.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boat")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boatId;

    private String name;

    private String category;

    private double length;

    private double beam;

    private double draft;

    private String flag;

    private String portRegistration;

    private String expirationDateInsurance;

    @ManyToOne
    @JoinColumn(name = "skipper_id")
    @JsonIgnore
    private Skipper skipper;

    @OneToMany(mappedBy = "boat")
    @JsonIgnore
    private List<Reservation> reservations;

    public Boat() {}

    public Boat(String name, String category, double length, double beam, double draft, String flag, String portRegistration, String expirationDateInsurance, Skipper skipper) {
        this.name = name;
        this.category = category;
        this.length = length;
        this.beam = beam;
        this.draft = draft;
        this.flag = flag;
        this.portRegistration = portRegistration;
        this.expirationDateInsurance = expirationDateInsurance;
        this.skipper = skipper;
    }

    public long getBoatId() {
        return boatId;
    }

    public void setBoatId(long boatId) {
        this.boatId = boatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPortRegistration() {
        return portRegistration;
    }

    public void setPortRegistration(String portRegistration) {
        this.portRegistration = portRegistration;
    }

    public String getExpirationDateInsurance() {
        return expirationDateInsurance;
    }

    public void setExpirationDateInsurance(String expirationDateInsurance) {
        this.expirationDateInsurance = expirationDateInsurance;
    }

    public Skipper getSkipper() {
        return skipper;
    }

    public void setSkipper(Skipper skipper) {
        this.skipper = skipper;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation) {
        if (reservations == null) {
            reservations = new ArrayList<>();
        }

        reservations.add(reservation);
        reservation.setBoat(this);
    }

    public void removeReservation(Reservation reservation) {
        if (reservations != null) {
            reservations.remove(reservation);
            reservation.setBoat(null);
        }
    }
}
