package org.ticketkings.movietheatresystem.model.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.model.showing.Showing;
import org.ticketkings.movietheatresystem.model.showing.ShowingService;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;
import org.ticketkings.movietheatresystem.model.showtime.ShowtimeService;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {

    private final MovieService movieService;
    private final ShowingService showingService;
    private final ShowtimeService showtimeService;

    @Autowired
    public MovieController(
            MovieService movieService,
            ShowingService showingService,
            ShowtimeService showtimeService
    ) {
        this.movieService = movieService;
        this.showingService = showingService;
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("{movieId}")
    public Movie getMovie(@PathVariable Integer movieId) {
        return movieService.getMovie(movieId);
    }

    @GetMapping("search")
    public List<Movie> searchMovies(@RequestParam String name) {
        return movieService.searchMovies(name);
    }

    @GetMapping("{movieId}/showtimes")
    public List<Showtime> getMovieShowtimes(@PathVariable Integer movieId) {
        List<Showing> showings = showingService.getShowingsByMovieId(movieId);
        List<Showtime> showtimes = new ArrayList<>();

        for (Showing showing: showings) {
            showtimes.add(showing.getShowtime());
        }

        return showtimes;
    }

    @PatchMapping("{movieId}/release")
    public Movie releaseMovie(@PathVariable Integer movieId) {
        List<Showtime> movieShowtimes = getMovieShowtimes(movieId);
        for (Showtime showtime: movieShowtimes) {
            showtime.setCapacity(10);
            showtimeService.updateShowtime(showtime);
        }

        return movieService.releaseMovie(movieId);
    }

    @GetMapping("released")
    public List<Movie> getReleasedMovies() {
        List<Movie> movies = getMovies();
        return movies.stream().filter(Movie::getIsReleased).toList();
    }
}
