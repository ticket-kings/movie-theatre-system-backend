package org.ticketkings.movietheatresystem.model.showing;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.ticketkings.movietheatresystem.model.Theatre.Theatre;
import org.ticketkings.movietheatresystem.model.movie.Movie;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "showing")
public class Showing {

    @Id
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "theatre_id")
    private int theatreId;

    @Column(name = "showtime_id")
    private int showtimeId;

    @JsonManagedReference
    @OneToMany(mappedBy = "showing")
    private List<Movie> movies;

    @JsonManagedReference
    @OneToMany(mappedBy = "showing")
    private List<Theatre> theatres;

    @JsonManagedReference
    @OneToMany(mappedBy = "showing")
    private List<Showtime> showtimes;

    public Showing(int movieId, int theatreId, int showtimeId) {
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.showtimeId = showtimeId;
    }

    public Showing() {
    }
}
