package org.ticketkings.movietheatresystem.model.user.registered;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Boundary class that is used to query the database for RegisteredUser objects
 */
@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {

    Optional<RegisteredUser> findByEmailAddress(String email);
}
