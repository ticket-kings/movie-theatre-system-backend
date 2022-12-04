package org.ticketkings.movietheatresystem.model.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Boundary class that is used to query the database for Ticket objects
 */
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    Ticket findByPaymentId(Integer paymentId);
}
