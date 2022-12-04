package org.ticketkings.movietheatresystem.model.showing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ticketkings.movietheatresystem.model.seat.Seat;

@Service
public class ShowingService {

    public final ShowingRepository showingRepository;

    @Autowired
    public ShowingService(ShowingRepository showingRepository) {
        this.showingRepository = showingRepository;
    }

    public List<Showing> getShowings() {
        return showingRepository.findAll();
    }

    public List<Showing> getShowingsByMovieId(Integer movieId) {
        return showingRepository.findAllByMovieId(movieId);
    }

    public Showing getShowingBySeat(Seat seat) {
        List<Showing> showings = showingRepository.findAllByShowtimeId(seat.getShowtimeId());

        return showings.stream().filter(s -> s.getShowtime().getSeats().contains(seat)).findFirst().get();
    }

    public void createDefaultShowing(Integer movieId, Integer showtimeId) {
        Showing showing = new Showing(movieId, 1, showtimeId);
        showingRepository.save(showing);
    }
}
