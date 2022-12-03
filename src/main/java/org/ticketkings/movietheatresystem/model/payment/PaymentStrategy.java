package org.ticketkings.movietheatresystem.model.payment;

/**
 * Defines a payment strategy algorithm template
 */
public interface PaymentStrategy {
    Payment makePayment(PaymentService paymentService, Payment payment);
}
