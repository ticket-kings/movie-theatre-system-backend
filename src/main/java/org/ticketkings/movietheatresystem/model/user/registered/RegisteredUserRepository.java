package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String> {

    RegisteredUser findByAddress(String address, String userId);

}
