package org.ticketkings.movietheatresystem.model.credit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreditService {

    private final CreditRepository creditRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public List<Credit> getCredits() {
        return creditRepository.findAll();
    }

    public Credit getCredit(Integer id) {
        Optional<Credit> optional = creditRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit with ID: " + id + " is invalid");
        }

        return optional.get();
    }

    public Credit saveCredit(Credit credit) {
        Optional<Credit> optional = creditRepository.findById(credit.getId());

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit with ID: " + credit.getId() + " is invalid");
        }

        return creditRepository.save(credit);
    }
}
