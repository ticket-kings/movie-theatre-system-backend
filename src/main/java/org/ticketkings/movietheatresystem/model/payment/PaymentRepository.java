package org.ticketkings.movietheatresystem.model.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Boundary class that is used to query the database for Payment objects
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
