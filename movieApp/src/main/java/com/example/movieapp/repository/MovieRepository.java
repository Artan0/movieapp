package com.example.movieapp.repository;

import com.example.movieapp.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByYrBetween(Integer yearFrom, Integer yearTo, Pageable pageable);

}

