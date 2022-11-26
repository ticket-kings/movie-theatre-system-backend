package org.ticketkings.movietheatresystem.model.seat;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SeatService {

    public final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeats() {
        return seatRepository.findAll();
    }

    public void cancelSeat(Integer seatId) {
        Optional<Seat> optional = seatRepository.findById(seatId);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Seat " + seatId + " does not exist");
        }

        Seat seat = optional.get();
        seat.cancelSeat();
        seatRepository.save(seat);
    }
}
