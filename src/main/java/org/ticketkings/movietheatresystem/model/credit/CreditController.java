package org.ticketkings.movietheatresystem.model.credit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/credit")
public class CreditController {

    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<Credit> getCredits() {
        return creditService.getCredits();
    }

    @GetMapping("/{creditId}")
    public Credit getCredit(@PathVariable Integer creditId) {
        return creditService.getCredit(creditId);
    }

    @GetMapping("/{creditId}/check/{price}")
    public Credit checkCredit(@PathVariable Integer creditId, @PathVariable Float price) {
        Credit credit = creditService.getCredit(creditId);
        credit.apply(price);
        return credit;
    }

}
