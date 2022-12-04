package org.ticketkings.movietheatresystem.model.user.guest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Boundary class that is used to query the database for GuestUser objects
 */
@Repository
public interface GuestUserRepository extends JpaRepository<GuestUser, Integer> {
}
