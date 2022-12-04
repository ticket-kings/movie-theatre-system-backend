package org.ticketkings.movietheatresystem.model.credit;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;
import org.ticketkings.movietheatresystem.model.user.User;

/**
 * Executes credit business logic and uses repository class to retrieve data from the database
 */
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

    public Credit checkCreditCode(String code) {
        Credit credit = getCreditByCode(code);

        if (credit.getExpired()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit with code: " + code.toUpperCase() + " is expired");
        }

        return credit;
    }

    public Credit getCreditByCode(String code) {
        Optional<Credit> optional = creditRepository.findByCode(code.toUpperCase());

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit with code: " + code.toUpperCase() + " does not exist");
        }

        return optional.get();
    }

    public Credit saveCredit(Credit credit) {
        Optional<Credit> optional = creditRepository.findById(credit.getId());

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit with ID: " + credit.getId() + " is invalid");
        }

        if (credit.getAmount() <= 0)
            return expireCredit(credit);

        return creditRepository.save(credit);
    }

    public Credit createCredit(User user, Showtime showtime, Seat seat) {
        Credit credit = new Credit();
        credit.calculate(user, showtime, seat);

        if (credit.getAmount() <= 0) {
            return credit;
        }

        scheduleExpiry(credit);

        return creditRepository.save(credit);
    }

    private void scheduleExpiry(Credit credit) {
        long timeUntilExpiry = credit.getExpiryDate().getTime() - System.currentTimeMillis();
        ScheduledExecutorService expireCredit = Executors.newSingleThreadScheduledExecutor();
        expireCredit.schedule(() -> expireCredit(credit), timeUntilExpiry, TimeUnit.MILLISECONDS);
    }

    public Credit expireCredit(Credit credit) {
        throwIfNotExists(credit.getId()); // check if credit exists
        credit.expireCredit();
        return creditRepository.save(credit);
    }

    public void throwIfNotExists(Integer id) {
        Optional<Credit> optional = creditRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit with ID: " + id + " does not exist");
        }
    }
}
