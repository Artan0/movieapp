package com.example.movieapp.service;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Review;

import java.util.List;

public interface ReviewService {
    Review addReviewMovie(Long id, String text);
    List<Review> getAllReviews();
}
