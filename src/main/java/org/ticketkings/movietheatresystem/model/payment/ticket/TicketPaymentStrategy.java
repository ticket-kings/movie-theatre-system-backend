package org.ticketkings.movietheatresystem.model.payment.ticket;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.ticketkings.movietheatresystem.model.payment.Payment;
import org.ticketkings.movietheatresystem.model.payment.PaymentService;
import org.ticketkings.movietheatresystem.model.payment.PaymentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketPaymentStrategy implements PaymentStrategy {

    private PaymentService paymentService;

    @Override
    public Payment makePayment(PaymentService paymentService, Payment payment) {
        this.paymentService = paymentService;
        checkExists(payment);
        payment.setPaymentDate(new Date());
        return this.paymentService.createPayment(payment);
    }

    public void checkExists(Payment payment) {
        Integer id = payment.getId();
        if (id!= null) {
            Optional<Payment> optional = paymentService.getPayment(payment.getId());
            if (optional.isEmpty())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TicketPayment with ID: " + id + " does not exist");
        }
    }
}
