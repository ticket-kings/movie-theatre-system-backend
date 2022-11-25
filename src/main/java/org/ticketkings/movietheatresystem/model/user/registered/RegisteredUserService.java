package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.ticketkings.movietheatresystem.model.user.guest.GuestUser;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    public RegisteredUser getRegisteredUser(Integer id) {
        Optional<RegisteredUser> optional = registeredUserRepository.findById(id);

        if(optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This student does not exist.");
        }

        return optional.get();
    }

    public List<RegisteredUser> getRegisteredUsers() {
        return registeredUserRepository.findAll();
    }

    public RegisteredUser createRegisteredUser(RegisteredUser registeredUser) {
        Optional<RegisteredUser> userById = registeredUserRepository
                .findById(registeredUser.getUserId());

        if (userById.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        return registeredUserRepository.save(registeredUser);
    }
}
