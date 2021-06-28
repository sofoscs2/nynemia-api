package com.nynemia.api.repository;

import com.nynemia.api.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {
}
