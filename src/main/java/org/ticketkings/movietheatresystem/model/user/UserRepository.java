package org.ticketkings.movietheatresystem.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Boundary class that is used to query the database for User objects
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByCardId(Integer cardId);
}
