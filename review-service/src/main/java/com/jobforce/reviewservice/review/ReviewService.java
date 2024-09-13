package com.jobforce.reviewservice.review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll(Long companyId);
    Review findReviewById(Long id);
    boolean addReview(Long companyId, Review review);
    boolean deleteReview(Long id);
    boolean updateReview(Long id, Review review);
}
