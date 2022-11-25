package org.ticketkings.movietheatresystem.model.credit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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

    public Credit(int id, float amount, Date expiryDate) {
        this.id = id;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public Credit() {

    }

}
