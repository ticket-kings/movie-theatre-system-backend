package org.ticketkings.movietheatresystem.model.showing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/showing")
public class ShowingController {

    private final ShowingService showingService;

    @Autowired
    public ShowingController(ShowingService showingService) {
        this.showingService = showingService;
    }

    public List<Showing> getShowings() {
        return showingService.getShowings();
    }
}
