package com.nynemia.api.repository;

import com.nynemia.api.model.Review;
import com.nynemia.api.model.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
}
