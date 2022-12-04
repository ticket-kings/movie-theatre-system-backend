package org.ticketkings.movietheatresystem.model.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Boundary class that is used to query the database for Card objects
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
