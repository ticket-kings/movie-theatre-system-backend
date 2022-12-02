package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ticketkings.movietheatresystem.model.card.Card;
import org.ticketkings.movietheatresystem.model.card.CardService;
import org.ticketkings.movietheatresystem.model.payment.PaymentService;
import org.ticketkings.movietheatresystem.model.payment.annual.AnnualPayment;
import org.ticketkings.movietheatresystem.model.payment.annual.AnnualPaymentStrategy;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;
import org.ticketkings.movietheatresystem.model.ticket.Ticket;
import org.ticketkings.movietheatresystem.model.ticket.TicketService;

@RestController
@RequestMapping(path = "api/v1/user/registered")
public class RegisteredUserController {

	private final RegisteredUserService registeredUserService;
	private final CardService cardService;
	private final PaymentService paymentService;
	private final TicketService ticketService;

	@Autowired
	public RegisteredUserController(
			RegisteredUserService registeredUserService,
			CardService cardService,
			PaymentService paymentService,
			TicketService ticketService
	) {
		this.registeredUserService = registeredUserService;
		this.cardService = cardService;
		this.paymentService = paymentService;
		this.ticketService = ticketService;
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
		paymentService.setPaymentStrategy(new AnnualPaymentStrategy());
		card.setPayments(List.of(paymentService.executePaymentStrategy(annualPayment)));

		registeredUser.setCardId(card.getId());
		return registeredUserService.createRegisteredUser(registeredUser);
	}

	@GetMapping("{userId}/tickets")
	public List<Ticket> getRegisteredUserTickets(@PathVariable Integer userId) {
		RegisteredUser user = registeredUserService.getRegisteredUser(userId);
		List<TicketPayment> payments = paymentService.getTicketPaymentsByCardId(user.getCardId());
		List<Ticket> tickets = new ArrayList<>();

		for (TicketPayment payment : payments) {
			tickets.add(ticketService.getTicketByPaymentId(payment.getId()));
		}
		
		return tickets;
	}

}
