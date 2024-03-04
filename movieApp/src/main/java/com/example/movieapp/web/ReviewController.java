package com.example.movieapp.web;

import com.example.movieapp.model.DTO.ReviewDTO;
import com.example.movieapp.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ReviewDTO>> getAllReviews(Pageable pageable) {
        Page<ReviewDTO> reviews = reviewService.getAllReviews(pageable);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

}
