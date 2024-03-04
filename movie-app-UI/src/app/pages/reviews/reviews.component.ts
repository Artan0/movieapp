import { Component, OnInit } from '@angular/core';
import { Review } from 'src/app/interfaces/review.interface';
import { ReviewService } from 'src/app/services/review.service';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.scss'],
})
export class ReviewsComponent implements OnInit {
  reviews: Review[] = [];

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.getAllReviews();
  }

  getAllReviews() {
    this.reviewService.getAllReviews().subscribe(
      (res) => {
        this.reviews = res;
      },
      (error) => {
        console.error('Error fetching reviews:', error);
      }
    );
  }
}
