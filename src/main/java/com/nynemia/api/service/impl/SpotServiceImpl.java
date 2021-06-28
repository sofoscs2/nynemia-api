package com.nynemia.api.service.impl;

import com.nynemia.api.model.Spot;
import com.nynemia.api.repository.SpotRepository;
import com.nynemia.api.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotRepository spotRepository;

    @Override
    public List<Spot> getSpots() {
        return spotRepository.findAll();
    }

    @Override
    public Optional<Spot> getSpotById(long id) {
        return spotRepository.findById(id);
    }

    @Override
    public Spot saveSpot(Spot spot) {
        return spotRepository.save(spot);
    }

    @Override
    public void deleteSpot(long id) {
        spotRepository.deleteById(id);
    }
}
