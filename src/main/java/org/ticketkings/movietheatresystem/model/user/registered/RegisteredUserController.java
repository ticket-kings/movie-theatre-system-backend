package org.ticketkings.movietheatresystem.model.user.registered;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user/registered")
public class RegisteredUserController {

	private final RegisteredUserService registeredUserService;

	@Autowired
	public RegisteredUserController(RegisteredUserService registeredUserService) {
		this.registeredUserService = registeredUserService;
	}

	@GetMapping("/{id}")
	public RegisteredUser getRegisteredUser(@PathVariable Integer id) {
		return registeredUserService.getRegisteredUser(id);
	}

	@GetMapping
	public List<RegisteredUser> getRegisteredUsers() {
		return registeredUserService.getRegisteredUsers();
	}

	@PostMapping
	public RegisteredUser addRegisteredUser(@RequestBody RegisteredUser registeredUser) {
		return registeredUserService.createRegisteredUser(registeredUser);
	}

}
