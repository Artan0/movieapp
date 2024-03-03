import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MoviesComponent } from './pages/movies/movies.component';
import { AddMovieComponent } from './pages/add-movie/add-movie.component';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
import { AddReviewComponent } from './pages/add-review/add-review.component';
import { ReviewsComponent } from './pages/reviews/reviews.component';

// const routes: Routes = [
//   {
//     path: 'movies',
//     children: [
//       { path: '', pathMatch: 'full', component: MoviesComponent },
//       { path: 'create', component: AddMovieComponent },
//       { path: ':id', component: MovieDetailsComponent },
//     ],
//   },
// ];
const routes: Routes = [
  { path: 'movies', component: MoviesComponent },
  { path: 'movies/create', component: AddMovieComponent },
  { path: 'movies/:id', component: MovieDetailsComponent },
  { path: 'movies/:id/reviews/create', component: AddReviewComponent },
  { path: 'reviews', component: ReviewsComponent },
  { path: '**', redirectTo: '/movies', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
