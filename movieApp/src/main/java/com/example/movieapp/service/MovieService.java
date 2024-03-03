package com.example.movieapp.service;

import com.example.movieapp.model.Movie;
import org.springframework.data.domain.Page;

public interface MovieService {

    Movie create(String title, String description, String genre, Integer year);


    Page<Movie> filter(String title, String genre, Integer year, Integer yearFrom, Integer yearTo,
                       int page, int pageSize, String sortField, String sortOrder);

    Movie getMovieById(Long id);

    Movie rateMovie(Long id, Double rating);

}
