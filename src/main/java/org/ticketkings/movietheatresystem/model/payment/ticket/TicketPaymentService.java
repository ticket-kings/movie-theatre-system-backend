package org.ticketkings.movietheatresystem.model.payment.ticket;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketPaymentService {

    private final TicketPaymentRepository ticketPaymentRepository;

    public TicketPaymentService(TicketPaymentRepository ticketPaymentRepository) {
        this.ticketPaymentRepository = ticketPaymentRepository;
    }

    public List<TicketPayment> getTicketPayments() {
        return ticketPaymentRepository.findAll();
    }

    public TicketPayment createTicketPayment(TicketPayment ticketPayment) {
        if (ticketPayment.getId() != null) {
            Optional<TicketPayment> optional = ticketPaymentRepository.findById(ticketPayment.getId());

            if (optional.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket Payment with ID: " + ticketPayment.getId() + " already exists");
            }
        }

        ticketPayment.setPaymentDate(new Date());
        return ticketPaymentRepository.save(ticketPayment);
    }
}
