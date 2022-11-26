package org.ticketkings.movietheatresystem.model.payment.annual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/payment/annual")
public class AnnualPaymentController {

    private final AnnualPaymentService annualPaymentService;

    @Autowired
    public AnnualPaymentController(AnnualPaymentService annualPaymentService) {
        this.annualPaymentService = annualPaymentService;
    }

    @GetMapping
    public List<AnnualPayment> getAnnualPayments() {
        return annualPaymentService.getAnnualPayments();
    }

}
