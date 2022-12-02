package org.ticketkings.movietheatresystem.model.ticket;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.credit.Credit;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.showing.Showing;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="payment_id")
    private Integer paymentId;

    @Column(name="seat_id")
    private Integer seatId;

    @Column(name="showing_id")
    private Integer showingId;

    @Column(name="credit_id")
    private Integer creditId;

    @OneToOne
    @JoinColumn(name = "payment_id", insertable = false, updatable = false)
    private TicketPayment payment;

    @OneToOne
    @JoinColumn(name = "seat_id", insertable = false, updatable = false)
    private Seat seat;

    @OneToOne
    @JoinColumn(name = "showing_id", insertable = false, updatable = false)
    private Showing showing;

    @OneToOne
    @JoinColumn(name = "credit_id", insertable = false, updatable = false)
    private Credit credit;

    public Ticket() {
    }

    public Ticket(Integer id, Integer paymentId, Integer seatId, Integer creditId) {
        this.id = id;
        this.paymentId = paymentId;
        this.seatId = seatId;
        this.creditId = creditId;
    }
}
