package org.ticketkings.movietheatresystem.model.payment.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Boundary class that is used to query the database for TicketPayment objects
 */
@Repository
public interface TicketPaymentRepository extends JpaRepository<TicketPayment, Integer> {

    List<TicketPayment> findByCardId(Integer cardId);
}
