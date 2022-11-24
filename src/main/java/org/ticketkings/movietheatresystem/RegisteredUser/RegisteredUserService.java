package org.ticketkings.movietheatresystem.RegisteredUser;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        Optional<RegisteredUser> userByEmail = registeredUserRepository
                .findRegisteredUser(registeredUser.getEmailAddress());

        if (userByEmail.isPresent()) {
            throw new IllegalStateException("User with this email already exists");
        }

        registeredUserRepository.save(registeredUser);
    }

    public void deleteRegisteredUser(String emailAddress) {
        Optional<RegisteredUser> foundUser = registeredUserRepository.findRegisteredUser(emailAddress);

        if (foundUser.isPresent()) {
            registeredUserRepository.deleteById(emailAddress);
        } else {
            throw new IllegalStateException("This user does not exist.");
        }
    }
}
