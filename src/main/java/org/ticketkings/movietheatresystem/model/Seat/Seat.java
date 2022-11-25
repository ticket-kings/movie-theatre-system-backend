package org.ticketkings.movietheatresystem.model.Seat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "seat")
public class Seat {

    @Id

    private String id;
    private String seatNumber;
    private int price;
    private boolean reserved;
    private boolean availible;

    public Seat(int price, String seatNumber, boolean reserved, boolean availible, String id) {
        this.price = price;
        this.seatNumber = seatNumber;
        this.reserved = reserved;
        this.availible = availible;
        this.id = id;
    }

    public void reserveSeat() {
        this.reserved = true;
    }
}
