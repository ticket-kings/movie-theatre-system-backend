package org.ticketkings.movietheatresystem.model.user.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.model.card.Card;
import org.ticketkings.movietheatresystem.model.card.CardService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user/guest")
public class GuestUserController {

    private final GuestUserService guestUserService;
    private final CardService cardService;

    @Autowired
    public GuestUserController(GuestUserService guestUserService, CardService cardService) {
        this.guestUserService = guestUserService;
        this.cardService = cardService;
    }

    @GetMapping
    public List<GuestUser> getGuestUsers() {
        return guestUserService.getGuestUsers();
    }

    @PostMapping
    public GuestUser createGuestUser(@RequestBody GuestUser guestUser) {
        Card card = guestUser.getCard();
        cardService.createCard(card);

        guestUser.setCardId(card.getId());
        return guestUserService.createGuestUser(guestUser);
    }

}
