package org.ticketkings.movietheatresystem.model.Showtime;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ticketkings.movietheatresystem.model.Seat.Seat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "showtime")
public class Showtime {

    @Id
    private Integer id;

    private Date time;

//    private ArrayList<Seat> seats;

    public Showtime(Integer id, Date time) {
        this.id = id;
        this.time = time;
    }

    public Showtime() {
    }
}
