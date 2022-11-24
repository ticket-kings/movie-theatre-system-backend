package org.ticketkings.movietheatresystem.RegisteredUser;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {
    
    private final RegisteredUserRepository registeredUserRepository;
    
    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    public List<RegisteredUser> getRegisteredUsers() {
        return registeredUserRepository.findAll();
    }

    public void addNewRegisteredUser(RegisteredUser registeredUser) {
        
    }
    
}
