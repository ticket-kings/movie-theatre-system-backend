package org.ticketkings.movietheatresystem.model.movie;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MovieService {

    public final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

}
