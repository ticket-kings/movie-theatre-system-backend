package org.ticketkings.movietheatresystem.model.showtime;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
