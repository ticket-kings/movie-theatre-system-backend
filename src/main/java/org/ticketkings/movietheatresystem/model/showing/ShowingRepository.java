package org.ticketkings.movietheatresystem.model.showing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, String> {
    List<Showing> findAllByMovieId(Integer movieId);

    List<Showing> findAllByShowtimeId(Integer showtimeId);
}
