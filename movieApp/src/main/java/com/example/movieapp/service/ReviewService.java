package com.example.movieapp.service;

import com.example.movieapp.model.Review;
import com.example.movieapp.model.DTO.ReviewDTO;

import java.util.List;

public interface ReviewService {
    Review addReviewMovie(Long id, String text);
    List<ReviewDTO> getAllReviews();
}
