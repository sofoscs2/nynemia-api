package com.nynemia.api.service;

import com.nynemia.api.model.PriceList;

import java.util.List;
import java.util.Optional;

public interface PriceListService {

    public List<PriceList> getPriceLists();
    public Optional<PriceList> getPriceListById(long id);
    public PriceList savePriceList(PriceList priceList);
    public void deletePriceList(long id);
}
