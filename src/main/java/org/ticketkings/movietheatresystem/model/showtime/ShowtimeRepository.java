package org.ticketkings.movietheatresystem.model.showtime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Boundary class that is used to query the database for Showtime objects
 */
@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {

}
