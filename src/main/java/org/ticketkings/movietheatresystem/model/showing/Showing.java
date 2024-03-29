package org.ticketkings.movietheatresystem.model.showing;

import javax.persistence.*;

import org.ticketkings.movietheatresystem.model.theatre.Theatre;
import org.ticketkings.movietheatresystem.model.movie.Movie;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;


import lombok.Getter;
import lombok.Setter;

/**
 * Represents the showing entity
 */
@Entity
@Getter
@Setter
@Table(name = "showing")
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "theatre_id")
    private Integer theatreId;

    @Column(name = "showtime_id")
    private Integer showtimeId;

    @OneToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @OneToOne
    @JoinColumn(name = "theatre_id", insertable = false, updatable = false)
    private Theatre theatre;

    @OneToOne
    @JoinColumn(name = "showtime_id", insertable = false, updatable = false)
    private Showtime showtime;

    public Showing(Integer movieId, Integer theatreId, Integer showtimeId) {
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.showtimeId = showtimeId;
    }

    public Showing() {
    }
}
