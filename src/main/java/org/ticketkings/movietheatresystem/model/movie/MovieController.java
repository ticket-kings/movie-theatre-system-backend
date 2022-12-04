package org.ticketkings.movietheatresystem.model.movie;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.email.EmailService;
import org.ticketkings.movietheatresystem.model.seat.SeatService;
import org.ticketkings.movietheatresystem.model.showing.Showing;
import org.ticketkings.movietheatresystem.model.showing.ShowingService;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;
import org.ticketkings.movietheatresystem.model.showtime.ShowtimeService;
import org.ticketkings.movietheatresystem.model.user.User;
import org.ticketkings.movietheatresystem.model.user.UserService;
import org.ticketkings.movietheatresystem.model.user.registered.RegisteredUser;
import org.ticketkings.movietheatresystem.model.user.registered.RegisteredUserService;

/**
 * Controller class that directs the movie API endpoints to the correct service functionality
 */
@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {

    private final MovieService movieService;
    private final ShowingService showingService;
    private final ShowtimeService showtimeService;
    private final SeatService seatService;
    private final RegisteredUserService registeredUserService;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public MovieController(
            MovieService movieService,
            ShowingService showingService,
            ShowtimeService showtimeService,
            SeatService seatService,
            RegisteredUserService registeredUserService,
            UserService userService,
            EmailService emailService
    ) {
        this.movieService = movieService;
        this.showingService = showingService;
        this.showtimeService = showtimeService;
        this.seatService = seatService;
        this.registeredUserService = registeredUserService;
        this.userService = userService;
        this.emailService = emailService;
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

        Movie movie = movieService.releaseMovie(movieId);
        List<User> users = userService.getUsers();
        emailService.sendMovieReleasedEmail(movie, users);

        return movie;
    }

    @GetMapping("released")
    public List<Movie> getReleasedMovies() {
        List<Movie> movies = getMovies();
        return movies.stream().filter(Movie::getIsReleased).toList();
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        List<Showtime> createdShowtimes = showtimeService.createDefaultShowtimes();
        for(Showtime showtime: createdShowtimes) {
            seatService.createDefaultSeats(showtime.getId());
            showingService.createDefaultShowing(createdMovie.getId(), showtime.getId());
        }

        List<RegisteredUser> users = registeredUserService.getRegisteredUsers();
        emailService.sendMovieCreatedEmail(movie, users);

        return createdMovie;
    }
}
