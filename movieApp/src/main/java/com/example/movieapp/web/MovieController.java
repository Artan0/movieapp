package com.example.movieapp.web;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Review;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ReviewService reviewService;

    public MovieController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movieRequest) {
        Movie movie = movieService.create(movieRequest.getTitle(), movieRequest.getDescription(),
                movieRequest.getGenre(), movieRequest.getYr());
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Movie>> listMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer yearFrom,
            @RequestParam(required = false) Integer yearTo,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortOrder) {
        Page<Movie> movies = movieService.filter(title, genre, year, yearFrom, yearTo, page, pageSize, sortField, sortOrder);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieDetails(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Movie> addRatingToMovie(@PathVariable Long id, @RequestBody Map<String, List<Double>> requestBody) {
        List<Double> ratings = requestBody.get("ratings");
        if (ratings == null || ratings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Double rating = ratings.get(0);
        Movie movie = movieService.rateMovie(id, rating);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


    @PostMapping("/{id}/review")
    public ResponseEntity<Review> addReviewToMovie(@PathVariable Long id, @RequestBody Review reviewRequest) {
        Review review = reviewService.addReviewMovie(id, reviewRequest.getReviewText());
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}
