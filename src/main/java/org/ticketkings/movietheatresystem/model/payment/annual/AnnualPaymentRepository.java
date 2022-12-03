package org.ticketkings.movietheatresystem.model.payment.annual;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Boundary class that is used to query the database for AnnualPayment objects
 */
@Repository
public interface AnnualPaymentRepository extends JpaRepository<AnnualPayment, Integer> {
}
