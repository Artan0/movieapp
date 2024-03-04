package com.example.movieapp.service.serviceImpl;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Review;
import com.example.movieapp.model.exceptions.MovieNotFoundException;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.service.MovieService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie create(String title, String description, String genre, Integer year) {
        List<Review>reviews = new ArrayList<>();
        List<Double> ratings = new ArrayList<>();
        Double averageRating = 0.0;
        Movie movie = new Movie(title, description, genre, year,ratings, averageRating, reviews);
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + id));
    }
    @Override
    public Movie rateMovie(Long id, Double rating) {
        Movie movie = getMovieById(id);
        movie.getRatings().add(rating);
        double sum = movie.getRatings().stream().mapToDouble(Double::doubleValue).sum();
        double average = sum / movie.getRatings().size();
        movie.setAverageRating(average);
        movieRepository.save(movie);
        return movie;
    }

    public Page<Movie> filter(String title, String genre, Integer year, Integer yearFrom, Integer yearTo,
                              int page, int pageSize, String sortField, String sortOrder) {
        Movie exampleMovie = new Movie(title, null, genre, year, null, null, null);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("genre", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase())
                .withMatcher("yr", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<Movie> example = Example.of(exampleMovie, exampleMatcher);
        Pageable pageable;

        if (sortField != null && sortOrder != null) {
            Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
            pageable = PageRequest.of(page - 1, pageSize, direction, sortField);
        } else {
            pageable = PageRequest.of(page - 1, pageSize);
        }

        Page<Movie> filteredMovies;


        if (yearFrom != null && yearTo != null) {
            filteredMovies = movieRepository.findByYrBetween(yearFrom, yearTo, pageable);
        } else {
            filteredMovies = movieRepository.findAll(example, pageable);
        }

        return filteredMovies;
    }

}

