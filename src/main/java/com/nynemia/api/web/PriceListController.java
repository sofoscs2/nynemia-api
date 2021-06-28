package com.nynemia.api.web;


import com.nynemia.api.model.PriceList;
import com.nynemia.api.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PriceListController {

    @Autowired
    private PriceListService priceListService;

    @GetMapping("/price-lists")
    public List<PriceList> getPriceLists() {
        return priceListService.getPriceLists();
    }

    @GetMapping("/price-lists/{id}")
    public Optional<PriceList> getPriceListById(@PathVariable long id) {
        return priceListService.getPriceListById(id);
    }
}
