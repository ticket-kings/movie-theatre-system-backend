package org.ticketkings.movietheatresystem.model.Showtime;

import java.util.List;

public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public List<Showtime> getShowtimes() {
        return showtimeRepository.findAll();
    }

}
