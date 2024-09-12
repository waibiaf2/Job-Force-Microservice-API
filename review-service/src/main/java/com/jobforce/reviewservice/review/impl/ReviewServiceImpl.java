package com.jobforce.reviewservice.review.impl;

import com.jobforce.reviewservice.review.Review;
import com.jobforce.reviewservice.review.ReviewRepository;
import com.jobforce.reviewservice.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else
            return false;
    }

    @Override
    public boolean deleteReview(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if(reviewOptional.isPresent()) {
            reviewRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateReview(Long id, Review review) {
        Review reviewToUpdate = findReviewById(id);

        if (reviewToUpdate != null) {
            reviewToUpdate.setTitle(review.getTitle());
            reviewToUpdate.setAuthor(review.getAuthor());
            reviewToUpdate.setBody(review.getBody());
            reviewToUpdate.setRating(review.getRating());

            reviewRepository.save(reviewToUpdate);
            return true;
        }else {
            return false;
        }
    }
}
