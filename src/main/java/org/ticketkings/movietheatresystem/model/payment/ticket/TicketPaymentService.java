package org.ticketkings.movietheatresystem.model.payment.ticket;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPaymentService {

    private final TicketPaymentRepository ticketPaymentRepository;

    public TicketPaymentService(TicketPaymentRepository ticketPaymentRepository) {
        this.ticketPaymentRepository = ticketPaymentRepository;
    }

    public List<TicketPayment> getTicketPayments() {
        return ticketPaymentRepository.findAll();
    }
}
