package org.ticketkings.movietheatresystem.model.credit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class that directs the credit API endpoints to the correct service functionality
 */
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

    @GetMapping("/{code}")
    public Credit getCredit(@PathVariable String code) {
        return creditService.getCreditByCode(code);
    }

    @GetMapping("/{code}/check")
    public Credit checkCredit(@PathVariable String code) {
        return creditService.checkCreditCode(code);
    }

}
