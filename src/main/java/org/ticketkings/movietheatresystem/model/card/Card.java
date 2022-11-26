package org.ticketkings.movietheatresystem.model.card;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table
public class Card {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="expiry_date")
    private String expiryDate;

    @Column(name="cvv")
    private String cvv;

    public Card() {

    }

    public Card(Integer id, String cardNumber, String expiryDate, String cvv) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

}
