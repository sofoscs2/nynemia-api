package com.nynemia.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "price_list")
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long priceListId;

    private double boatLength;

    private double annualPrice;

    @ManyToOne
    @JoinColumn(name = "marina_id")
    @JsonIgnore
    private Marina marina;


    public PriceList() {}

    public PriceList(double boatLength, double annualPrice) {
        this.boatLength = boatLength;
        this.annualPrice = annualPrice;
    }

    public long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(long priceListId) {
        this.priceListId = priceListId;
    }

    public double getBoatLength() {
        return boatLength;
    }

    public void setBoatLength(double boatLength) {
        this.boatLength = boatLength;
    }

    public double getAnnualPrice() {
        return annualPrice;
    }

    public void setAnnualPrice(double annualPrice) {
        this.annualPrice = annualPrice;
    }

    public Marina getMarina() {
        return marina;
    }

    public void setMarina(Marina marina) {
        this.marina = marina;
    }
}
