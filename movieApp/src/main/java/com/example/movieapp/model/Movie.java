package com.example.movieapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String genre;
    private Integer yr;
    @ElementCollection
    private List<Double> ratings;
    private Double averageRating;
    @JsonManagedReference
    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    public Movie() {}

    public Movie(String title, String description, String genre, Integer yr, List<Double> ratings, Double averageRating, List<Review> reviews) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.yr = yr;
        this.ratings = ratings;
        this.averageRating = averageRating;
        this.reviews = reviews;
    }
}
