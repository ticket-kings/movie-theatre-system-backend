package org.ticketkings.movietheatresystem.model.ticket;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicket(Integer ticketId) {
        Optional<Ticket> optional = ticketRepository.findById(ticketId);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket with ID: " + ticketId + "does not exist");
        }

        return optional.get();
    }

    public void deleteTicket(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    public Ticket createTicket(Ticket ticket) {
        if (ticket.getId() != null) {
            Optional<Ticket> optional = ticketRepository.findById(ticket.getId());

            if (optional.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket with ID: " + ticket.getId() + " already exists");
            }
        }

        return ticketRepository.save(ticket);
    }
}
