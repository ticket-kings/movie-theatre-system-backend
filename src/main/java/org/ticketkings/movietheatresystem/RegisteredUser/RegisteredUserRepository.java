package org.ticketkings.movietheatresystem.RegisteredUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String> {

    @Query("SELECT r from RegisteredUser r WHERE r.emailAddress = ?1")
    Optional<RegisteredUser> findRegisteredUser(String emailAddress);

}
