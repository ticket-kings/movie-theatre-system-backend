package org.ticketkings.movietheatresystem.model.theatre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Executes theatre business logic and uses repository class to retrieve data from the database
 */
@Service
public class TheatreService {

    public final TheatreRepository theatreRepository;

    @Autowired
    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getTheatres() {
        return theatreRepository.findAll();
    }

}
