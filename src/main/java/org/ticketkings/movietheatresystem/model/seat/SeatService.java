package org.ticketkings.movietheatresystem.model.seat;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.ticketkings.movietheatresystem.model.user.User;
import org.ticketkings.movietheatresystem.model.user.guest.GuestUser;

/**
 * Executes seat business logic and uses repository class to retrieve data from the database
 */
@Service
public class SeatService {

    public final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeats() {
        return seatRepository.findAll();
    }

    public Seat getSeat(Integer id) {
        return seatRepository.getReferenceById(id);
    }

    public Seat cancelSeat(Integer seatId) {
        Seat seat = getSeat(seatId);
        seat.cancelSeat();
        return seatRepository.save(seat);
    }

    public Seat reserveSeat(Integer seatId) {
        Seat seat = getSeat(seatId);

        if (seat.getReserved()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat with ID: " + seatId + " is already reserved");
        }

        seat.reserveSeat();
        return seatRepository.save(seat);
    }

    public void throwIfNotExists(Integer id) {
        Optional<Seat> optional = seatRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat with ID: " + id + " does not exist");
        }
    }

    public void throwIfReserved(Integer id) {
        Seat seat = getSeat(id);

        if (seat.getReserved()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat with ID: " + id + " is already reserved");
        }
    }

    public void createDefaultSeats(Integer showtimeId) {
        for (int i = 1; i <= 5; i++) {
            Seat seat = new Seat("A" + i, 10F, false);
            seat.setShowtimeId(showtimeId);
            seatRepository.save(seat);
        }

        for (int i = 1; i <= 5; i++) {
            Seat seat = new Seat("B" + i, 10F, false);
            seat.setShowtimeId(showtimeId);
            seatRepository.save(seat);
        }
    }
}
