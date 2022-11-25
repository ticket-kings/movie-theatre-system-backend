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

    public RegisteredUser login(Integer id, String password) {
        Optional<RegisteredUser> optional = registeredUserRepository.findById(id);

        if(optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This registered user id does not exist.");
        }

        RegisteredUser user = optional.get();
        if(!Objects.equals(user.getPassword(), password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is incorrect.");
        }

        return user;
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
