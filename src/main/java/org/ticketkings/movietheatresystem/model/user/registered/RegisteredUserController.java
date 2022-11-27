package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.model.card.Card;
import org.ticketkings.movietheatresystem.model.card.CardService;

@RestController
@RequestMapping(path = "api/v1/user/registered")
public class RegisteredUserController {

	private final RegisteredUserService registeredUserService;
	private final CardService cardService;

	@Autowired
	public RegisteredUserController(
			RegisteredUserService registeredUserService,
			CardService cardService
	) {
		this.registeredUserService = registeredUserService;
		this.cardService = cardService;
	}

	@GetMapping("/login")
	public RegisteredUser login(@RequestParam String email, @RequestParam String password) {
		return registeredUserService.login(email, password);
	}

	@GetMapping
	public List<RegisteredUser> getRegisteredUsers() {
		return registeredUserService.getRegisteredUsers();
	}

	@PostMapping
	public RegisteredUser createRegisteredUser(@RequestBody RegisteredUser registeredUser) {
		registeredUserService.throwErrorIfExists(registeredUser.getEmailAddress());

		Card card = registeredUser.getCard();
		cardService.createCard(card);

		registeredUser.setCardId(card.getId());
		return registeredUserService.createRegisteredUser(registeredUser);
	}

}
