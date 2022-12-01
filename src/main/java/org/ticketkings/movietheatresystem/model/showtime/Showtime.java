package org.ticketkings.movietheatresystem.model.showtime;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.ticketkings.movietheatresystem.model.seat.Seat;

@Entity
@Getter
@Setter
@Table(name = "showtime")
public class Showtime {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "time")
    private Date time;

    @Column(name = "reserved_seats")
    private Integer reservedSeats;

    @Column(name = "capacity")
    private Integer capacity;

    @JsonManagedReference(value = "showtime-seat")
    @OneToMany(mappedBy = "showtime")
    private List<Seat> seats;

    public Showtime(Integer id, Date time, Integer reservedSeats, Integer capacity) {
        this.id = id;
        this.time = time;
        this.reservedSeats = reservedSeats;
        this.capacity = capacity;
    }

    public Showtime() {
    }

    public void incrementSeats() {
        if (reservedSeats < capacity)
            reservedSeats++;
    }

    public void decrementSeats() {
        if (reservedSeats <= 0) return;

        reservedSeats--;
    }
}
