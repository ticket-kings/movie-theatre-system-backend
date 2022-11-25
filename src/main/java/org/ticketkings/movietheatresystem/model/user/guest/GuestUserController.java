package org.ticketkings.movietheatresystem.model.user.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user/guest")
public class GuestUserController {

    private final GuestUserService guestUserService;

    @Autowired
    public GuestUserController(GuestUserService guestUserService) {
        this.guestUserService = guestUserService;
    }

    @GetMapping
    public List<GuestUser> getGuestUsers() {
        return guestUserService.getGuestUsers();
    }

    @PostMapping
    public GuestUser createGuestUser(@RequestBody GuestUser guestUser) {
        return guestUserService.createGuestUser(guestUser);
    }

}
