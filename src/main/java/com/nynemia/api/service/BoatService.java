package com.nynemia.api.service;

import com.nynemia.api.model.Boat;

import java.util.List;
import java.util.Optional;

public interface BoatService {

    public List<Boat> getBoats();
    public Optional<Boat> getBoatById(long id);
    public Boat saveBoat(Boat boat);
    public void deleteBoat(long id);
}
