package com.nynemia.api.model;


import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;

    private String arriveDate;

    private String departureDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;

    @ManyToOne
    @JoinColumn(name = "spot_id")
    private Spot spot;

    public Reservation() {
    }

    public Reservation(String arriveDate, String departureDate, String status) {
        this.arriveDate = arriveDate;
        this.departureDate = departureDate;
        this.status = status;
    }

    public long getReservationId() {
        return reservationId;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", arriveDate='" + arriveDate + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", status='" + status + '\'' +
                ", boat=" + boat.getBoatId() +
                ", spot=" + spot.getSpotId() +
                '}';
    }
}
