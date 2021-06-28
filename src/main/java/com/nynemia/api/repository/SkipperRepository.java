package com.nynemia.api.repository;

import com.nynemia.api.model.Skipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkipperRepository extends JpaRepository<Skipper, Long> {
}
