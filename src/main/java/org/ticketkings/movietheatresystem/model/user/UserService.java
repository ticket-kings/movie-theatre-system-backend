package org.ticketkings.movietheatresystem.model.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Executes user business logic and uses repository class to retrieve data from the database
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with ID: " + id + " does not exist");
        }

        return optional.get();
    }

    public User getUserByCardId(Integer cardId) {
        Optional<User> optional = userRepository.findByCardId(cardId);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with Card ID: " + cardId + " does not exist");
        }

        return optional.get();
    }

    public void deleteUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with ID: " + id + " does not exist");
        }

        userRepository.delete(optional.get());
    }
}
