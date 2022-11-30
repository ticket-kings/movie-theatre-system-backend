package org.ticketkings.movietheatresystem.model.payment.annual;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class AnnualPaymentService {

    private final AnnualPaymentRepository annualPaymentRepository;

    public AnnualPaymentService(AnnualPaymentRepository annualPaymentRepository) {
        this.annualPaymentRepository = annualPaymentRepository;
    }

    public List<AnnualPayment> getAnnualPayments() {
        return annualPaymentRepository.findAll();
    }

    public AnnualPayment createAnnualPayment(AnnualPayment annualPayment) {
        annualPayment.setPaymentDate(new Date());
        scheduleNextPayment(annualPayment);
        return annualPaymentRepository.save(annualPayment);
    }

    private void scheduleNextPayment(AnnualPayment currentPayment) {
        currentPayment.setNextPaymentDate(oneYearFromNow());
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            AnnualPayment nextPayment = new AnnualPayment();
            nextPayment.setAmount(currentPayment.getAmount());
            nextPayment.setCardId(currentPayment.getCardId());
            nextPayment.setNextPaymentDate(oneYearFromNow());
            createAnnualPayment(nextPayment);
        }, 365, 365, TimeUnit.DAYS);
    }

    private Date oneYearFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

}
