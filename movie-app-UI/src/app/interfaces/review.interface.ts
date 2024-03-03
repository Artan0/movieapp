import { Movie } from './movie.interface';

export interface Review {
  id: number;
  reviewText: string;
  movie: Movie;
}
