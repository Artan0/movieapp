package com.example.movieapp.model.DTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String reviewText;
    private MovieDTO movie;
    public ReviewDTO() {
    }
}
