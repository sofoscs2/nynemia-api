package com.nynemia.api.service;

import com.nynemia.api.model.Spot;

import java.util.List;
import java.util.Optional;

public interface SpotService {

    public List<Spot> getSpots();
    public Optional<Spot> getSpotById(long id);
    public Spot saveSpot(Spot spot);
    public void deleteSpot(long id);
}
