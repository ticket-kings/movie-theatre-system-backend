package org.ticketkings.movietheatresystem.model.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.card.Card;

import javax.persistence.*;
import java.util.Date;

/**
 * Represents the payment entity
 */
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="amount")
    private Float amount;

    @Column(name="payment_date")
    private Date paymentDate;

    @Column(name="card_id")
    private Integer cardId;

    @JsonBackReference(value = "card-payment")
    @ManyToOne
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    private Card card;
}
