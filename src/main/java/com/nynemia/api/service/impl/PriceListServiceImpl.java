package com.nynemia.api.service.impl;

import com.nynemia.api.model.PriceList;
import com.nynemia.api.repository.PriceListRepository;
import com.nynemia.api.service.PriceListService;
import com.nynemia.api.web.exception.PriceListNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceListServiceImpl implements PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    @Override
    public List<PriceList> getPriceLists() {
        return priceListRepository.findAll();
    }

    @Override
    public Optional<PriceList> getPriceListById(long id) {
        return priceListRepository.findById(id);
    }

    @Override
    public PriceList savePriceList(PriceList priceList) {
        return priceListRepository.save(priceList);
    }

    @Override
    public void deletePriceList(long id) {
        priceListRepository.findById(id)
                .orElseThrow(PriceListNotFoundException::new);

        priceListRepository.deleteById(id);
    }
}
