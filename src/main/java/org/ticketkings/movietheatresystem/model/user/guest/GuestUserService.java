package org.ticketkings.movietheatresystem.model.user.guest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GuestUserService {

    private final GuestUserRepository guestUserRepository;

    public GuestUserService(GuestUserRepository guestUserRepository) {
        this.guestUserRepository = guestUserRepository;
    }

    public List<GuestUser> getGuestUsers() {
        return guestUserRepository.findAll();
    }

    public GuestUser createGuestUser(GuestUser guestUser) {
        Optional<GuestUser> userById = guestUserRepository
                .findById(guestUser.getUserId());

        if (userById.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        return guestUserRepository.save(guestUser);
    }
}
