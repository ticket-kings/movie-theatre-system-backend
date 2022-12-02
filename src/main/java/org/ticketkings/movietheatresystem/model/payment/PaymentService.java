package org.ticketkings.movietheatresystem.model.payment;

import org.springframework.stereotype.Service;
import org.ticketkings.movietheatresystem.model.payment.annual.AnnualPayment;
import org.ticketkings.movietheatresystem.model.payment.annual.AnnualPaymentRepository;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TicketPaymentRepository ticketPaymentRepository;
    private final AnnualPaymentRepository annualPaymentRepository;
    private PaymentStrategy paymentStrategy;

    public PaymentService(
            PaymentRepository paymentRepository,
            TicketPaymentRepository ticketPaymentRepository,
            AnnualPaymentRepository annualPaymentRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.ticketPaymentRepository = ticketPaymentRepository;
        this.annualPaymentRepository = annualPaymentRepository;
    }

    public List<TicketPayment> getTicketPayments() {
        return ticketPaymentRepository.findAll();
    }

    public List<AnnualPayment> getAnnualPayments() {
        return annualPaymentRepository.findAll();
    }

    public Optional<Payment> getPayment(Integer id) {
        return paymentRepository.findById(id);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public Payment executePaymentStrategy(Payment payment) {
        return paymentStrategy.makePayment(this, payment);
    }
}
