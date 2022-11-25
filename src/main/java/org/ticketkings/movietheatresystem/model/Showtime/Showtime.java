package org.ticketkings.movietheatresystem.model.Showtime;

import java.util.ArrayList;

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

    ArrayList<Seat> seats;
    private int id, time;

    public Showtime(int id, int time) {
        this.id = id;
        this.time = time;
    }
}
