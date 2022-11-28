package org.ticketkings.movietheatresystem.model.ticket;

import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.model.credit.Credit;
import org.ticketkings.movietheatresystem.model.credit.CreditService;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPaymentService;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.seat.SeatService;
import org.ticketkings.movietheatresystem.model.user.User;
import org.ticketkings.movietheatresystem.model.user.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final SeatService seatService;
    private final TicketPaymentService ticketPaymentService;
    private final UserService userService;
    private final CreditService creditService;

    public TicketController(
            TicketService ticketService,
            SeatService seatService,
            TicketPaymentService ticketPaymentService,
            UserService userService,
            CreditService creditService
    ) {
        this.ticketService = ticketService;
        this.seatService = seatService;
        this.ticketPaymentService = ticketPaymentService;
        this.userService = userService;
        this.creditService = creditService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @DeleteMapping("/{ticketId}")
    public void cancelTicket(@PathVariable Integer ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);

        seatService.cancelSeat(ticket.getSeatId());

        ticketService.deleteTicket(ticket);
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        User user = userService.getUserByCardId(ticket.getPayment().getCardId());
        Seat seat = seatService.reserveSeat(user, ticket.getSeatId());
        ticket.setSeat(seat);

        Float price = seat.getPrice();

        if (ticket.getCreditId() != null) {
            Credit credit = creditService.getCredit(ticket.getCreditId());
            price = credit.apply(price);
            creditService.saveCredit(credit);
            ticket.setCredit(credit);
        }

        ticket.getPayment().setAmount(price);
        TicketPayment payment = ticketPaymentService.createTicketPayment(ticket.getPayment());
        ticket.setPaymentId(payment.getId());
        ticket.setPayment(payment);

        return ticketService.createTicket(ticket);
    }
}
