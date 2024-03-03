import { Injectable } from '@angular/core';
import { ApiResponse, ApiService } from './api.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../interfaces/movie.interface';
import { Page } from '../interfaces/page.interface';

@Injectable({
  providedIn: 'root',
})
export class MovieService extends ApiService {
  constructor(private http: HttpClient) {
    super();
  }
  getAllMovies(
    title?: string,
    genre?: string,
    year?: number,
    yearFrom?: number,
    yearTo?: number,
    page: number = 1,
    pageSize: number = 5,
    sortField?: string,
    sortOrder?: string
  ): Observable<Page<Movie>> {
    let params = new HttpParams();
    if (title) params = params.set('title', title);
    if (genre) params = params.set('genre', genre);
    if (year !== undefined) params = params.set('year', year.toString());
    if (yearFrom !== undefined)
      params = params.set('yearFrom', yearFrom.toString());
    if (yearTo !== undefined) params = params.set('yearTo', yearTo.toString());
    params = params.set('page', page.toString());
    params = params.set('pageSize', pageSize.toString());
    if (sortField) params = params.set('sortField', sortField);
    if (sortOrder) params = params.set('sortOrder', sortOrder);

    return this.http.get<Page<Movie>>(this.getUrl('movies/list'), { params });
  }

  getMovieById(id: number): Observable<Movie> {
    return this.http.get<Movie>(this.getUrl(`movies/${id}`));
  }

  addMovie(body: any): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.getUrl('movies/add'), body);
  }

  addRating(movieId: number, rating: number): Observable<ApiResponse> {
    const payload = { ratings: [rating] };
    console.log('Request payload:', payload);

    return this.http.post<ApiResponse>(
      this.getUrl(`movies/${movieId}/rate`),
      payload
    );
  }
}
export { Page };
