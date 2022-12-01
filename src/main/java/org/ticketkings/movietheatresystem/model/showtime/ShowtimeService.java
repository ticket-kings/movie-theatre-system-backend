package org.ticketkings.movietheatresystem.model.showtime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public List<Showtime> getShowtimes() {
        return showtimeRepository.findAll();
    }

    public Showtime decrementSeats(Integer id) {
        Showtime showtime = findByIdOrError(id);
        showtime.decrementSeats();
        return showtimeRepository.save(showtime);
    }

    public Showtime incrementSeats(Integer id) {
        Showtime showtime = findByIdOrError(id);
        showtime.incrementSeats();
        return showtimeRepository.save(showtime);
    }

    private Showtime findByIdOrError(Integer id) {
        Optional<Showtime> optional = showtimeRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Showtime with ID: " + id + " does not exist");
        }

        return optional.get();
    }

    public void throwIfMaxCapacity(Integer id) {
        Showtime showtime = findByIdOrError(id);
        if (showtime.getReservedSeats() >= showtime.getCapacity())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Showtime with ID: " + id + " is already at max capacity");
    }
}
