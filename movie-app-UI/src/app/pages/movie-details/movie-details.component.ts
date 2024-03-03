import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/interfaces/movie.interface';
import { MovieService } from 'src/app/services/movies.service';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss'],
})
export class MovieDetailsComponent implements OnInit {
  movie!: Movie;
  userRating!: number;
  userReviewText!: string;
  reviewForm!: FormGroup;

  constructor(
    private movieService: MovieService,
    private reviewService: ReviewService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.getMovieDetails();
  }

  private getMovieDetails(): void {
    const movieIdString = this.route.snapshot.paramMap.get('id');

    if (movieIdString !== null) {
      const movieId = +movieIdString;

      if (!isNaN(movieId)) {
        this.movieService.getMovieById(movieId).subscribe(
          (movie: Movie) => {
            this.movie = movie;
          },
          (error) => {
            console.error('Error fetching movie details:', error);
          }
        );
      } else {
        console.error('Invalid movie ID:', movieIdString);
      }
    } else {
      console.error('Movie ID not found in the route parameters.');
    }
  }
  submitRating(): void {
    if (this.userRating) {
      this.movieService.addRating(this.movie.id, this.userRating).subscribe(
        (response) => {
          console.log('Rating added successfully:', response);
          this.getMovieDetails();
        },
        (error) => {
          console.error('Error adding rating:', error);
        }
      );
    }
  }
}
