package org.ticketkings.movietheatresystem.model.theatre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Boundary class that is used to query the database for Theatre objects
 */
@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

}
