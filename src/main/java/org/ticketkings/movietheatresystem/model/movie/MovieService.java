package org.ticketkings.movietheatresystem.model.movie;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MovieService {

    public final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovie(Integer id) {
        Optional<Movie> optional = movieRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with ID: " + id + " does not exist");
        }

        return optional.get();
    }

    public List<Movie> searchMovies(String name) {
        return movieRepository.searchMovies(name);
    }

    public Movie releaseMovie(Integer id) {
        Optional<Movie> optional = movieRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie with ID: " + id + " does not exist");
        }

        Movie movie = optional.get();
        movie.releaseMovie();
        return movieRepository.save(movie);
    }

}
