package com.example.movieapp.config;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.Review;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer {

    private final MovieService movieService;
    private final ReviewService reviewService;

    @Autowired
    public DataInitializer(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @PostConstruct
    public void initializeData() {
        Movie movie1 = createMovie("The Shawshank Redemption", "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.", "Drama", 1994);
        Movie movie2 = createMovie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", "Crime", 1972);
        Movie movie3 = createMovie("The Dark Knight", "When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.", "Action", 2008);
        Movie movie4 = createMovie("Pulp Fiction", "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.", "Crime", 1994);
        Movie movie5 = createMovie("Schindler's List", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.", "Biography", 1993);
        Movie movie6 = createMovie("Forrest Gump", "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75.", "Drama", 1994);
        Movie movie7 = createMovie("The Matrix", "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", "Action", 1999);
        Movie movie8 = createMovie("The Lord of the Rings: The Return of the King", "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.", "Adventure", 2003);
        Movie movie9 = createMovie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.", "Action", 2010);
        Movie movie10 = createMovie("The Shawshank Redemption", "In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.", "Animation", 2008);
        Movie movie11 = createMovie("The Silence of the Lambs", "A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.", "Crime", 1991);
        Movie movie12 = createMovie("Titanic", "A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.", "Drama", 1997);
        Movie movie13 = createMovie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", "Action", 2009);
        Movie movie14 = createMovie("The Great Gatsby", "A writer and wall street trader, Nick, finds himself drawn to the past and lifestyle of his millionaire neighbor, Jay Gatsby.", "Drama", 2013);

    }

    private Movie createMovie(String title, String description, String genre, Integer year) {
        return movieService.create(title, description, genre, year);
    }
}
