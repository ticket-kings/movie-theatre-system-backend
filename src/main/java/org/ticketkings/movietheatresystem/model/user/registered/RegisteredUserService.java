package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.List;
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

    public List<RegisteredUser> getRegisteredUsers() {
        return registeredUserRepository.findAll();
    }

    public void addNewRegisteredUser(RegisteredUser registeredUser) {
//        Optional<RegisteredUser> userByEmail = registeredUserRepository
//                .findByEmailAddress(registeredUser.getEmailAddress());
//
//        if (userByEmail.isPresent()) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists");
//        }
//
//        registeredUserRepository.save(registeredUser);
    }

    public void deleteRegisteredUser(String emailAddress) {
//        Optional<RegisteredUser> foundUser = registeredUserRepository.findByEmailAddress(emailAddress);
//
//        if (foundUser.isPresent()) {
//            registeredUserRepository.deleteById(emailAddress);
//        } else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist");
//        }
    }
}
