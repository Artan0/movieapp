import { HttpClient } from '@angular/common/http';
import { ApiResponse, ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { Review } from '../interfaces/review.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ReviewService extends ApiService {
  constructor(private http: HttpClient) {
    super();
  }

  getAllReviews() {
    return this.http.get<Review[]>(this.getUrl('reviews/list'));
  }

  addReview(movieId: number, reviewText: string): Observable<ApiResponse> {
    const body = { movieId, reviewText };
    return this.http.post<ApiResponse>(
      this.getUrl(`movies/${movieId}/review`),
      body
    );
  }
}
