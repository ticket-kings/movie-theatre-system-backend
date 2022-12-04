package org.ticketkings.movietheatresystem.model.showtime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Executes showtime business logic and uses repository class to retrieve data from the database
 */
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

    public Showtime updateShowtime(Showtime showtime) {
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

    public List<Showtime> createDefaultShowtimes() {
        Showtime showtime1 = new Showtime(daysFromNow(2), 0, 2);
        Showtime createdShowtime1 = showtimeRepository.save(showtime1);

        Showtime showtime2 = new Showtime(daysFromNow(4), 0, 2);
        Showtime createdShowtime2 = showtimeRepository.save(showtime2);

        List<Showtime> showtimeList = new ArrayList<>();
        showtimeList.add(createdShowtime1);
        showtimeList.add(createdShowtime2);

        return showtimeList;
    }

    private Date daysFromNow(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
}
