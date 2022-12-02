package org.ticketkings.movietheatresystem.model.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findByPaymentId(Integer paymentId);
}
