package com.example.movieapp.service.serviceImpl;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Review;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.ReviewRepository;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final MovieRepository movieRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieService movieService, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    @Override
    public Review addReviewMovie(Long id, String text) {
        Movie movie = movieService.getMovieById(id);
        Review review = new Review(movie,text);
        movie.getReviews().add(review);
//        movieRepository.save(movie);
        reviewRepository.save(review);
        return review;
    }
}
