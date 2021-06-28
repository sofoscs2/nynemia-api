package com.nynemia.api.web;

import com.nynemia.api.model.Boat;
import com.nynemia.api.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BoatController {

    @Autowired
    private BoatService boatService;

    @GetMapping("/boats")
    public List<Boat> getBoats() {
        return boatService.getBoats();
    }

    @GetMapping("/boats/{id}")
    public Optional<Boat> getBoatById(@PathVariable long id) {
        return boatService.getBoatById(id);
    }
}
