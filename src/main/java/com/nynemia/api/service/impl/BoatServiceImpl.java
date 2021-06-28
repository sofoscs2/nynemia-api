package com.nynemia.api.service.impl;

import com.nynemia.api.model.Boat;
import com.nynemia.api.repository.BoatRepository;
import com.nynemia.api.service.BoatService;
import com.nynemia.api.web.exception.BoatNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    private BoatRepository boatRepository;

    @Override
    public List<Boat> getBoats() {
        return boatRepository.findAll();
    }

    @Override
    public Optional<Boat> getBoatById(long id) {
        return boatRepository.findById(id);
    }

    @Override
    public Boat saveBoat(Boat boat) {
        return boatRepository.save(boat);
    }

    @Override
    public void deleteBoat(long id) {
        boatRepository.findById(id)
                .orElseThrow(BoatNotFoundException::new);
        boatRepository.deleteById(id);
    }
}
