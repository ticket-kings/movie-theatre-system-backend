package org.ticketkings.movietheatresystem.model.showing;

import javax.persistence.*;

import org.ticketkings.movietheatresystem.model.theatre.Theatre;
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
    @OneToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "theatre_id", insertable = false, updatable = false)
    private Theatre theatre;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "showtime_id", insertable = false, updatable = false)
    private Showtime showtime;

    public Showing(int movieId, int theatreId, int showtimeId) {
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.showtimeId = showtimeId;
    }

    public Showing() {
    }
}
