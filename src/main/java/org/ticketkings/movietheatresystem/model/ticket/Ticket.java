package org.ticketkings.movietheatresystem.model.ticket;

import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.payment.Payment;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;
import org.ticketkings.movietheatresystem.model.seat.Seat;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="ticket")
public class Ticket {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="payment_id")
    private Integer paymentId;

    @Column(name="seat_id")
    private Integer seatId;

    @OneToOne
    @JoinColumn(name = "payment_id", insertable = false, updatable = false)
    private TicketPayment payment;

    @OneToOne
    @JoinColumn(name = "seat_id", insertable = false, updatable = false)
    private Seat seat;

    public Ticket() {
    }

    public Ticket(Integer id, Integer paymentId, Integer seatId) {
        this.id = id;
        this.paymentId = paymentId;
        this.seatId = seatId;
    }
}
