package org.ticketkings.movietheatresystem.model.credit;

import java.time.LocalDateTime;
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

        if (credit.getAmount() <= 0) {
            creditRepository.delete(credit);
            credit.setId(null);
            return credit;
        }

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
        expireCredit.schedule(() -> deleteCredit(credit), timeUntilExpiry, TimeUnit.MILLISECONDS);
    }

    public void deleteCredit(Credit credit) {
        getCredit(credit.getId()); // check if credit exists
        creditRepository.delete(credit);
    }
}
