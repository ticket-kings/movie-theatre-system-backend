package org.ticketkings.movietheatresystem.model.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    Movie findByName(String name, String id);

}
