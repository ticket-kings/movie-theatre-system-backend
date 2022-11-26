package org.ticketkings.movietheatresystem.model.payment.annual;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnualPaymentService {

    private final AnnualPaymentRepository annualPaymentRepository;

    public AnnualPaymentService(AnnualPaymentRepository annualPaymentRepository) {
        this.annualPaymentRepository = annualPaymentRepository;
    }

    public List<AnnualPayment> getAnnualPayments() {
        return annualPaymentRepository.findAll();
    }

}
