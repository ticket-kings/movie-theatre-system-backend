package org.ticketkings.movietheatresystem.model.credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Integer> {

    Optional<Credit> findByCode(String code);
}
