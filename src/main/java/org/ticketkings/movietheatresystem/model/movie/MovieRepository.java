package org.ticketkings.movietheatresystem.model.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Boundary class that is used to query the database for Movie objects
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("select m from Movie m where m.name like %:name%")
    List<Movie> searchMovies(String name);
}
