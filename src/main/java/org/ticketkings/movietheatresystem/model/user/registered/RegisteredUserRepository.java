package org.ticketkings.movietheatresystem.model.user.registered;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {

//    RegisteredUser findByAddress(String address, String userId);

}
