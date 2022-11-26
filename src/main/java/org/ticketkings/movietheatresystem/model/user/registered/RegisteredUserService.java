package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    public RegisteredUser login(String email, String password) {
        Optional<RegisteredUser> optional = registeredUserRepository.findByEmailAddress(email);

        if(optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The registered user email does not exist");
        }

        RegisteredUser user = optional.get();
        if(!Objects.equals(user.getPassword(), password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is incorrect");
        }

        return user;
    }

    public List<RegisteredUser> getRegisteredUsers() {
        return registeredUserRepository.findAll();
    }

    public RegisteredUser createRegisteredUser(RegisteredUser registeredUser) {
        if (registeredUser.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id is not required to create a registered user");
        }

        Optional<RegisteredUser> optional = registeredUserRepository.findByEmailAddress(registeredUser.getEmailAddress());
        if (optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email already exists.");
        }

        return registeredUserRepository.save(registeredUser);
    }
}
