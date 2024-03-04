import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from 'src/app/interfaces/movie.interface';
import { MovieService, Page } from 'src/app/services/movies.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.scss'],
})
export class MoviesComponent implements OnInit {
  movies: Movie[] = [];
  filterTitle: string = '';
  filterGenre: string = '';
  filterYear: number | undefined = undefined;
  filterYearFrom: number | undefined = undefined;
  filterYearTo: number | undefined = undefined;
  sortField: string | undefined = undefined;
  sortOrder: string | undefined = undefined;
  pageSize: number = 5;
  currentPage: number = 1;
  totalPages: number = 1;
  displayedColumns: string[] = [
    'title',
    'description',
    'genre',
    'averageRating',
    'yr',
  ];

  constructor(
    private movieService: MovieService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params) => {
      this.filterTitle = params['title'] ?? '';
      this.filterGenre = params['genre'] ?? '';
      this.filterYear = params['year'] ? parseInt(params['year']) : undefined;
      this.filterYearFrom = params['yearFrom']
        ? parseInt(params['yearFrom'])
        : undefined;
      this.filterYearTo = params['yearTo']
        ? parseInt(params['yearTo'])
        : undefined;
      this.currentPage = params['page'] ? parseInt(params['page']) : 1;

      this.filterMovies();
    });
  }

  filterMovies(): void {
    this.movieService
      .getAllMovies(
        this.filterTitle,
        this.filterGenre,
        this.filterYear,
        this.filterYearFrom,
        this.filterYearTo,
        this.currentPage,
        this.pageSize,
        this.sortField,
        this.sortOrder
      )
      .subscribe(
        (page: Page<Movie>) => {
          this.movies = page.content;
          this.totalPages = page.totalPages;
        },
        (error) => {
          console.error('Error fetching or filtering movies:', error);
        }
      );
  }

  updateFilters(params: any): void {
    params.page = this.currentPage;
    params.pageSize = this.pageSize;
    params.sortField = this.sortField;
    params.sortOrder = this.sortOrder;
    this.router.navigate(['/movies'], { queryParams: params });
  }

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    this.router.navigate([], {
      relativeTo: this.activatedRoute,
      queryParams: { page: this.currentPage },
      queryParamsHandling: 'merge',
    });
    this.filterMovies();
  }
}
