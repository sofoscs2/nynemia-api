package com.nynemia.api.service;

import com.nynemia.api.model.Review;
import com.nynemia.api.model.ReviewId;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    public List<Review> getReviews();
    public Optional<Review> getReviewById(ReviewId reviewId);
    public Review saveReview(Review review);
    public void deleteReview(ReviewId reviewId);
}
