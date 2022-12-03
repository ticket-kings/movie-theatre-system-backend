package org.ticketkings.movietheatresystem.model.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ticketkings.movietheatresystem.model.payment.annual.AnnualPayment;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;

import java.util.List;

/**
 * Controller class that directs the payment API endpoints to the correct service functionality
 */
@RestController
@RequestMapping(path = "api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("ticket")
    public List<TicketPayment> getTicketPayments() {
        return paymentService.getTicketPayments();
    }

    @GetMapping("annual")
    public List<AnnualPayment> getAnnualPayments() {
        return paymentService.getAnnualPayments();
    }
}
