package org.ticketkings.movietheatresystem.model.Showing;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ticketkings.movietheatresystem.model.Showtime.Showtime;
import org.ticketkings.movietheatresystem.model.movie.Movie;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "showing")
public class Showing {

    @Id

    private Movie movie;
    private Showtime showtime;

    public Showing(Movie movie, Showtime showtime) {
        this.movie = movie;
        this.showtime = showtime;
    }
}
