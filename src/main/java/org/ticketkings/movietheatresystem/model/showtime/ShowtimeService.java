package org.ticketkings.movietheatresystem.model.showtime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.ticketkings.movietheatresystem.model.seat.Seat;

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

    public Showtime getShowtime(Integer id) {
        Optional<Showtime> optional = showtimeRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Showtime with ID: " + id + " does not exist");
        }

        return optional.get();
    }

}
