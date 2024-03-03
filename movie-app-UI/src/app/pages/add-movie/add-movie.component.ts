import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MovieService } from 'src/app/services/movies.service';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss'],
})
export class AddMovieComponent implements OnInit {
  movieForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private movieService: MovieService,
    private router: Router
  ) {}

  ngOnInit() {
    this.movieForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      genre: ['', Validators.required],
      yr: [0, [Validators.required, Validators.min(1900)]],
    });
  }

  onSubmit() {
    if (this.movieForm.valid) {
      const movie = {
        title: this.movieForm.value.title,
        description: this.movieForm.value.description,
        genre: this.movieForm.value.genre,
        yr: this.movieForm.value.yr,
      };
      this.movieService.addMovie(movie).subscribe(
        (response) => {
          console.log('Movie added successfully:', response);
          this.router.navigate(['/movies']);
        },
        (error) => {
          console.error('Error adding movie:', error);
        }
      );
    }
  }
}
