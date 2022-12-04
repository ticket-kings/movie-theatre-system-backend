package org.ticketkings.movietheatresystem.model.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Boundary class that is used to query the database for Seat objects
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
