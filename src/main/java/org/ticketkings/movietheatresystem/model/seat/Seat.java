package org.ticketkings.movietheatresystem.model.seat;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;

@Entity
@Getter
@Setter
@Table(name = "seat")
public class Seat {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="seat_number")
    private String seatNumber;

    @Column(name="price")
    private Integer price;

    @Column(name="premium")
    private Boolean premium;

    @Column(name="reserved")
    private Boolean reserved;

    @Column(name="showtime_id")
    private Integer showtimeId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="showtime_id", insertable = false, updatable = false)
    private Showtime showtime;

    public Seat(Integer id, String seatNumber, Integer price, Boolean premium, Boolean reserved) {
        this.id = id;
        this.price = price;
        this.seatNumber = seatNumber;
        this.premium = premium;
        this.reserved = reserved;
    }

    public Seat() {
    }

    public void reserveSeat() {
        this.reserved = true;
    }
}
