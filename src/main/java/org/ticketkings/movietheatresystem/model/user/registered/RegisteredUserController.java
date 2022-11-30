package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.model.card.Card;
import org.ticketkings.movietheatresystem.model.card.CardService;
import org.ticketkings.movietheatresystem.model.payment.annual.AnnualPayment;
import org.ticketkings.movietheatresystem.model.payment.annual.AnnualPaymentController;
import org.ticketkings.movietheatresystem.model.payment.annual.AnnualPaymentService;

@RestController
@RequestMapping(path = "api/v1/user/registered")
public class RegisteredUserController {

	private final RegisteredUserService registeredUserService;
	private final CardService cardService;
	private final AnnualPaymentService annualPaymentService;

	@Autowired
	public RegisteredUserController(
			RegisteredUserService registeredUserService,
			CardService cardService,
			AnnualPaymentService annualPaymentService
	) {
		this.registeredUserService = registeredUserService;
		this.cardService = cardService;
		this.annualPaymentService = annualPaymentService;
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

		AnnualPayment annualPayment = new AnnualPayment();
		annualPayment.setCardId(card.getId());
		annualPayment.setAmount(20F);
		card.setPayments(List.of(annualPaymentService.createAnnualPayment(annualPayment)));

		registeredUser.setCardId(card.getId());
		return registeredUserService.createRegisteredUser(registeredUser);
	}

}
