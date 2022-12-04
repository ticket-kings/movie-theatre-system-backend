package org.ticketkings.movietheatresystem.model.card;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.payment.Payment;

import javax.persistence.*;
import java.util.List;

/**
 * Represents the card entity
 */
@Entity
@Getter
@Setter
@Table
public class Card {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="expiry_date")
    private String expiryDate;

    @Column(name="cvv")
    private String cvv;

    @JsonManagedReference(value = "card-payment")
    @OneToMany(mappedBy = "card")
    private List<Payment> payments;

    public Card() {
    }

    public Card(Integer id, String cardNumber, String expiryDate, String cvv) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

}
