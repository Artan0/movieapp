package com.example.movieapp.service;

import com.example.movieapp.model.Review;
import com.example.movieapp.model.DTO.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Review addReviewMovie(Long id, String text);
    Page<ReviewDTO> getAllReviews(Pageable pageable);
}
