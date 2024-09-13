package com.jobforce.reviewservice.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewService reviewService;

    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.findAll(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable("reviewId") Long reviewId) {
        Review review = reviewService.findReviewById(reviewId);

        if (review == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(
        @RequestParam Long companyId,
        @RequestBody Review review
    ) {
        boolean reviewCreated = reviewService.addReview(companyId, review);

        if (reviewCreated)
            return new ResponseEntity<>(
                "Review created successfully",
                HttpStatus.CREATED
            );
        else
            return new ResponseEntity<>(
                "Review already exists",
                HttpStatus.CONFLICT
            );
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(
        @PathVariable("reviewId") Long reviewId,
        @RequestBody Review review
    ) {
        boolean reviewUpdated = reviewService.updateReview(reviewId, review);

        if (reviewUpdated)
            return new ResponseEntity<>(
                "Review updated successfully",
                HttpStatus.OK
            );
        else
            return new ResponseEntity<>(
                "Review Was Not Updated",
                HttpStatus.NOT_FOUND
            );
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId) {
        boolean reviewDeleted = reviewService.deleteReview(reviewId);
        if (reviewDeleted)
            return new ResponseEntity<>(
                "Review Deleted Successfully",
                HttpStatus.OK
            );
        else
            return new ResponseEntity<>(
                "Review Was Not Deleted",
                HttpStatus.NOT_FOUND
            );
    }
}
