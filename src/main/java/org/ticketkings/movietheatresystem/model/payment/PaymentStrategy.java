package org.ticketkings.movietheatresystem.model.payment;

public interface PaymentStrategy {
    Payment makePayment(PaymentService paymentService, Payment payment);
}
