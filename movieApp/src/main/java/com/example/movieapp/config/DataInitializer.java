package com.example.movieapp.config;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Review;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer {

    private final MovieService movieService;
    private final ReviewService reviewService;

    @Autowired
    public DataInitializer(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @PostConstruct
    public void initializeData() {
        Movie movie1 = createMovie("Movie 1", "Description 1", "Genre 1", 2000);
        Movie movie2 = createMovie("Movie 2", "Description 2", "Genre 2", 2001);
        Movie movie3 = createMovie("Movie 3", "Description 3", "Genre 3", 2002);
        Movie movie4 = createMovie("Movie 4", "Description 4", "Genre 4", 2003);
        Movie movie5 = createMovie("Movie 5", "Description 5", "Genre 6", 2004);
        Movie movie6 = createMovie("Movie 6", "Description 6", "Genre 2", 2006);
        Movie movie7 = createMovie("Movie 7", "Description 7", "Genre 3", 2007);
        Movie movie8 = createMovie("Movie 8", "Description 8", "Genre 4", 2009);
        Movie movie9 = createMovie("Movie 9", "Description 9", "Genre 5", 2016);
        Movie movie10 = createMovie("Movie 10", "Description 10", "Genre 1", 2020);

    }

    private Movie createMovie(String title, String description, String genre, Integer year) {
        return movieService.create(title, description, genre, year);
    }
}
