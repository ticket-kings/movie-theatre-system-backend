package org.ticketkings.movietheatresystem.model.payment.ticket;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.payment.Payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name="payment_id")
@Table(name="ticket_payment")
public class TicketPayment extends Payment {

    @JsonBackReference
    @Column(name="payment_id", insertable = false, updatable = false)
    private Integer paymentId;
}
