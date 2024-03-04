package com.example.movieapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonBackReference
    private Movie movie;
    private String reviewText;

    public Review() {}

    public Review(Movie movie, String reviewText) {
        this.movie = movie;
        this.reviewText = reviewText;
    }

}
