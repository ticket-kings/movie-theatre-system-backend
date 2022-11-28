package org.ticketkings.movietheatresystem.model.ticket;

import org.springframework.web.bind.annotation.*;
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


    public TicketController(
            TicketService ticketService,
            SeatService seatService,
            TicketPaymentService ticketPaymentService,
            UserService userService
    ) {
        this.ticketService = ticketService;
        this.seatService = seatService;
        this.ticketPaymentService = ticketPaymentService;
        this.userService = userService;
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

        TicketPayment payment = ticketPaymentService.createTicketPayment(ticket.getPayment());
        ticket.setPaymentId(payment.getId());
        ticket.setPayment(payment);

        return ticketService.createTicket(ticket);
    }
}
