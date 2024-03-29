package org.ticketkings.movietheatresystem.model.showtime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class that directs the showtime API endpoints to the correct service functionality
 */
@RestController
@RequestMapping("api/v1/showtime")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public List<Showtime> getShowtimes() {
        return showtimeService.getShowtimes();
    }

}
