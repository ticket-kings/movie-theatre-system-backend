package org.ticketkings.movietheatresystem.model.payment.ticket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/payment/ticket")
public class TicketPaymentController {

    private final TicketPaymentService ticketPaymentService;

    public TicketPaymentController(TicketPaymentService ticketPaymentService) {
        this.ticketPaymentService = ticketPaymentService;
    }

    @GetMapping
    public List<TicketPayment> getTicketPayments() {
        return ticketPaymentService.getTicketPayments();
    }
}
