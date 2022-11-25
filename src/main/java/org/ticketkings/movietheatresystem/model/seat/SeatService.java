package org.ticketkings.movietheatresystem.model.seat;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SeatService {

    public final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getSeats() {
        return seatRepository.findAll();
    }
}
