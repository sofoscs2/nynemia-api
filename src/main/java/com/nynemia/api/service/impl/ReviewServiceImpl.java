package com.nynemia.api.service.impl;

import com.nynemia.api.model.Review;
import com.nynemia.api.model.ReviewId;
import com.nynemia.api.repository.ReviewRepository;
import com.nynemia.api.service.ReviewService;
import com.nynemia.api.web.exception.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(ReviewId reviewId) {
        return reviewRepository.findById(reviewId);
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(ReviewId reviewId) {
        reviewRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);

        reviewRepository.deleteById(reviewId);
    }
}
