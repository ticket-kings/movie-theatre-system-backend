package org.ticketkings.movietheatresystem.model.credit;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.ticket.Ticket;

@Entity
@Getter
@Setter
@Table(name = "credit")
public class Credit {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @OneToOne(mappedBy = "credit")
    @JsonBackReference(value = "ticket-credit")
    private Ticket ticket;

    public Credit(int id, float amount, Date expiryDate) {
        this.id = id;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public Credit() {

    }

    public Float apply(Float price) {
        float newPrice = price - amount;
        float newAmount = amount - price;
        amount = newAmount < 0 ? 0 : newAmount;
        return newPrice < 0 ? 0 : newPrice;
    }

}
