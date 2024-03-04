package com.example.movieapp.service.serviceImpl;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.DTO.MovieDTO;
import com.example.movieapp.model.Review;
import com.example.movieapp.model.DTO.ReviewDTO;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.ReviewRepository;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieService movieService) {
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
    }
    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private ReviewDTO convertToDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setReviewText(review.getReviewText());

        Movie movie = review.getMovie();
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setYr(movie.getYr());
        movieDTO.setRatings(movie.getRatings());
        movieDTO.setAverageRating(movie.getAverageRating());
        reviewDTO.setMovie(movieDTO);

        return reviewDTO;
    }
    @Override
    public Review addReviewMovie(Long id, String text) {
        Movie movie = movieService.getMovieById(id);
        Review review = new Review(movie,text);
        movie.getReviews().add(review);
        reviewRepository.save(review);
        return review;
    }
}
