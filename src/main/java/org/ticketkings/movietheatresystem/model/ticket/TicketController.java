package org.ticketkings.movietheatresystem.model.ticket;

import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.model.credit.Credit;
import org.ticketkings.movietheatresystem.model.credit.CreditService;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPaymentService;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.seat.SeatService;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;
import org.ticketkings.movietheatresystem.model.showtime.ShowtimeService;
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
    private final ShowtimeService showtimeService;

    public TicketController(
            TicketService ticketService,
            SeatService seatService,
            TicketPaymentService ticketPaymentService,
            UserService userService,
            CreditService creditService,
            ShowtimeService showtimeService
    ) {
        this.ticketService = ticketService;
        this.seatService = seatService;
        this.ticketPaymentService = ticketPaymentService;
        this.userService = userService;
        this.creditService = creditService;
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @DeleteMapping("/{ticketId}")
    public Credit cancelTicket(@PathVariable Integer ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);

        Seat seat = seatService.cancelSeat(ticket.getSeatId());

        ticketService.deleteTicket(ticket);

        Showtime showtime = showtimeService.getShowtime(ticket.getSeat().getShowtimeId());
        User user = userService.getUserByCardId(ticket.getPayment().getCardId());

        return creditService.createCredit(user, showtime, seat);
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
            credit = creditService.saveCredit(credit);
            ticket.setCreditId(credit.getId());
        }

        ticket.getPayment().setAmount(price);
        TicketPayment payment = ticketPaymentService.createTicketPayment(ticket.getPayment());
        ticket.setPaymentId(payment.getId());
        ticket.setPayment(payment);

        return ticketService.createTicket(ticket);
    }
}
