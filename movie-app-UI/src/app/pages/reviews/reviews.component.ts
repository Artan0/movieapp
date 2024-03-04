import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Review } from 'src/app/interfaces/review.interface';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.scss'],
})
export class ReviewsComponent implements OnInit {
  dataSource: MatTableDataSource<Review> = new MatTableDataSource<Review>();
  displayedColumns: string[] = [
    'reviewText',
    'movieTitle',
    'description',
    'averageRating',
  ];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.getAllReviews(0, 10, 'id', 'asc');
  }

  getAllReviews(
    page: number,
    pageSize: number,
    sortField: string,
    sortOrder: string
  ) {
    this.reviewService
      .getAllReviews(page, pageSize, sortField, sortOrder)
      .subscribe(
        (res) => {
          this.dataSource = new MatTableDataSource(res.content);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        (error) => {
          console.error('Error fetching reviews:', error);
        }
      );
  }
}
