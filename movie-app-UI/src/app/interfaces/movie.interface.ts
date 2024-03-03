import { Review } from './review.interface';

export interface Movie {
  id: number;
  title: string;
  description: string;
  genre: string;
  yr: number;
  ratings: number[];
  averageRating: number;
  reviews: Review[];
}
