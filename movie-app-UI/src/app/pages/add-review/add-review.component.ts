import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/interfaces/movie.interface';
import { MovieService } from 'src/app/services/movies.service';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.scss'],
})
export class AddReviewComponent {
  movie!: Movie;
  reviewForm!: FormGroup;

  constructor(
    private movieService: MovieService,
    private reviewService: ReviewService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.getMovieDetails();
    this.initReviewForm();
  }
  private initReviewForm(): void {
    this.reviewForm = this.formBuilder.group({
      reviewText: ['', Validators.required],
    });
  }
  submitReview(): void {
    if (this.reviewForm.valid) {
      const reviewText = this.reviewForm.get('reviewText')?.value;

      this.reviewService.addReview(this.movie.id, reviewText).subscribe(
        (response) => {
          console.log('Review added successfully:', response);
          this.reviewForm.reset();
        },
        (error) => {
          console.error('Error adding review:', error);
        }
      );
    }
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
}
