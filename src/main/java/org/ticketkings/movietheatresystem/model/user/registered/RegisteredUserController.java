package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/v1/user/registered")
public class RegisteredUserController {

	private final RegisteredUserService registeredUserService;

	@Autowired
	public RegisteredUserController(RegisteredUserService registeredUserService) {
		this.registeredUserService = registeredUserService;
	}

	@GetMapping
	public List<RegisteredUser> getRegisteredUsers() {
		return registeredUserService.getRegisteredUsers();
	}

	@PostMapping
	public void addRegisteredUser(@RequestBody RegisteredUser registeredUser) {
		registeredUserService.addNewRegisteredUser(registeredUser);
	}

	@DeleteMapping(path = "/{emailAddress}")
	public void deleteRegisteredUser(@PathVariable("emailAddress") String emailAddress) {
		registeredUserService.deleteRegisteredUser(emailAddress);
	}

}
