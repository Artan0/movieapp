package com.example.movieapp.model.DTO;

import lombok.Data;

import java.util.List;
@Data
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private Integer yr;
    private List<Double> ratings;
    private Double averageRating;
    public MovieDTO() {
    }
}

