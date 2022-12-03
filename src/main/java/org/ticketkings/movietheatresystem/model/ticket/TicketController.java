package org.ticketkings.movietheatresystem.model.ticket;

import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.email.EmailService;
import org.ticketkings.movietheatresystem.model.credit.Credit;
import org.ticketkings.movietheatresystem.model.credit.CreditService;
import org.ticketkings.movietheatresystem.model.payment.Payment;
import org.ticketkings.movietheatresystem.model.payment.PaymentService;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPaymentStrategy;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.seat.SeatService;
import org.ticketkings.movietheatresystem.model.showing.Showing;
import org.ticketkings.movietheatresystem.model.showing.ShowingService;
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
    private final PaymentService paymentService;
    private final UserService userService;
    private final CreditService creditService;
    private final ShowtimeService showtimeService;
    private final ShowingService showingService;
    private final EmailService emailService;

    public TicketController(
            TicketService ticketService,
            SeatService seatService,
            PaymentService paymentService,
            UserService userService,
            CreditService creditService,
            ShowtimeService showtimeService,
            ShowingService showingService,
            EmailService emailService
    ) {
        this.ticketService = ticketService;
        this.seatService = seatService;
        this.paymentService = paymentService;
        this.userService = userService;
        this.creditService = creditService;
        this.showtimeService = showtimeService;
        this.showingService = showingService;
        this.emailService = emailService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable Integer ticketId) {
        return ticketService.getTicket(ticketId);
    }

    @DeleteMapping("/{ticketId}")
    public Credit cancelTicket(@PathVariable Integer ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);

        Seat seat = seatService.cancelSeat(ticket.getSeatId());

        ticketService.deleteTicket(ticket);

        Showtime showtime = showtimeService.decrementSeats(ticket.getSeat().getShowtimeId());
        User user = userService.getUserByCardId(ticket.getPayment().getCardId());

        Credit credit = creditService.createCredit(user, showtime, seat);
        credit.createCreditEmail(user, emailService);

        return credit;
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        checkCreateTicketErrors(ticket);

        Seat seat = reserveSeat(ticket.getSeatId());
        ticket.setSeat(seat);

        Float price = seat.getPrice();
        if (ticket.getCreditId() != null) {
            Credit credit = creditService.getCredit(ticket.getCreditId());
            price = credit.apply(price);
            credit = creditService.saveCredit(credit);
            ticket.setCreditId(credit.getId());
        }

        ticket.getPayment().setAmount(price);
        paymentService.setPaymentStrategy(new TicketPaymentStrategy());
        Payment payment = paymentService.executePaymentStrategy(ticket.getPayment());
        ticket.setPaymentId(payment.getId());
        ticket.setPayment((TicketPayment) payment);

        Showing showing = showingService.getShowingBySeat(seat);
        ticket.setShowingId(showing.getId());
        ticket.setShowing(showing);

        User user = userService.getUserByCardId(payment.getCardId());
        ticket.sendTicketPurchasedEmail(user, emailService);

        return ticketService.createTicket(ticket);
    }

    private void checkCreateTicketErrors(Ticket ticket) {
        userService.getUserByCardId(ticket.getPayment().getCardId());
        seatService.throwIfNotExists(ticket.getSeatId());
        seatService.throwIfReserved(ticket.getSeatId());
        Seat seat = seatService.getSeat(ticket.getSeatId());
        showtimeService.throwIfMaxCapacity(seat.getShowtimeId());
        if(ticket.getCreditId() != null)
            creditService.throwIfNotExists(ticket.getCreditId());
    }

    private Seat reserveSeat(Integer seatId) {
        Seat seat = seatService.getSeat(seatId);
        if (!seat.getReserved())
            showtimeService.incrementSeats(seat.getShowtimeId());

        return seatService.reserveSeat(seatId);
    }
}
