import { HttpClient, HttpParams } from '@angular/common/http';
import { ApiResponse, ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { Review } from '../interfaces/review.interface';
import { Observable } from 'rxjs';
import { Page } from '../interfaces/page.interface';

@Injectable({
  providedIn: 'root',
})
export class ReviewService extends ApiService {
  constructor(private http: HttpClient) {
    super();
  }

  getAllReviews(
    page: number,
    pageSize: number,
    sortField: string,
    sortOrder: string
  ): Observable<Page<Review>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('pageSize', pageSize.toString())
      .set('sortField', sortField)
      .set('sortOrder', sortOrder);

    return this.http.get<Page<Review>>(this.getUrl('reviews/list'), { params });
  }

  addReview(movieId: number, reviewText: string): Observable<ApiResponse> {
    const body = { movieId, reviewText };
    return this.http.post<ApiResponse>(
      this.getUrl(`movies/${movieId}/review`),
      body
    );
  }
}
