package org.ticketkings.movietheatresystem.model.ticket;

import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.model.seat.SeatService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final SeatService seatService;

    public TicketController(TicketService ticketService, SeatService seatService) {
        this.seatService = seatService;
        this.ticketService = ticketService;
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
}
