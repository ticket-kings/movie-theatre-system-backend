package org.ticketkings.movietheatresystem.model.payment.annual;

import org.ticketkings.movietheatresystem.model.payment.Payment;
import org.ticketkings.movietheatresystem.model.payment.PaymentService;
import org.ticketkings.movietheatresystem.model.payment.PaymentStrategy;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnnualPaymentStrategy implements PaymentStrategy {
    @Override
    public Payment makePayment(PaymentService paymentService, Payment payment) {
        payment.setPaymentDate(new Date());
        scheduleNextPayment(paymentService, (AnnualPayment) payment);
        return paymentService.createPayment(payment);
    }

    private void scheduleNextPayment(PaymentService paymentService, AnnualPayment currentPayment) {
        currentPayment.setNextPaymentDate(oneYearFromNow());
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            AnnualPayment nextPayment = new AnnualPayment();
            nextPayment.setAmount(currentPayment.getAmount());
            nextPayment.setCardId(currentPayment.getCardId());
            nextPayment.setNextPaymentDate(oneYearFromNow());
            makePayment(paymentService, nextPayment);
        }, 365, 365, TimeUnit.DAYS);
    }

    private Date oneYearFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }
}
