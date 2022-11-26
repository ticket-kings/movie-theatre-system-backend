package org.ticketkings.movietheatresystem.model.payment.annual;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.payment.Payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name="payment_id")
@Table(name="annual_payment")
public class AnnualPayment extends Payment {

    @JsonBackReference
    @Column(name="payment_id", insertable = false, updatable = false)
    private Integer paymentId;

    @Column(name="next_payment_date")
    private Date nextPaymentDate;

}
