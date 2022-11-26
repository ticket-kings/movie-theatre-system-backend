package org.ticketkings.movietheatresystem.model.payment.annual;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualPaymentRepository extends JpaRepository<AnnualPayment, Integer> {
}
