package org.ticketkings.movietheatresystem.model.credit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.email.EmailDetails;
import org.ticketkings.movietheatresystem.email.EmailService;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;
import org.ticketkings.movietheatresystem.model.ticket.Ticket;
import org.ticketkings.movietheatresystem.model.user.User;
import org.ticketkings.movietheatresystem.model.user.guest.GuestUser;
import org.ticketkings.movietheatresystem.model.user.registered.RegisteredUser;

@Entity
@Getter
@Setter
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @OneToOne(mappedBy = "credit")
    @JsonBackReference
    private Ticket ticket;

    public Credit(int id, float amount, Date expiryDate) {
        this.id = id;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public Credit() {

    }

    public Float apply(Float price) {
        float newPrice = price - amount;
        float newAmount = amount - price;
        amount = newAmount < 0 ? 0 : newAmount;
        return newPrice < 0 ? 0 : newPrice;
    }

    public void calculate(User user, Showtime showtime, Seat seat) {
        Date now = new Date();
        Date time = showtime.getTime();
        if (now.after(time)) {
            amount = (float) 0;
            return;
        }

        if (user instanceof RegisteredUser)
            amount = seat.getPrice();
        else if (user instanceof GuestUser && isMoreThan3DaysFromNow(time))
            amount = seat.getPrice() * (float) 0.85;
        else {
            amount = (float) 0;
            return;
        }

        expiryDate = oneYearFromNow();
    }

    private boolean isMoreThan3DaysFromNow(Date time) {
        Date now = new Date();
        long milli = time.getTime() - now.getTime();
        long hours = milli / (3600 * 1000);
        return hours >= 72;
    }

    private Date oneYearFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    public void createCreditEmail(User user, EmailService emailService) {
        emailService.sendEmail(new EmailDetails(
                        user.getEmailAddress(),
                        "Ticket Cancellation Confirmation",
                        amount == 0 ? createNoCreditEmailBody(user) : createCreditEmailBody(user)
                )
        );
    }

    private String createCreditEmailBody(User user) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy h:mm aa z");
        return user.getName() + ",\n\n" +
                "This is a confirmation that your ticket has been cancelled.\n\n" +
                "You have received a credit of $" + String.format("%.2f", amount) + " that can be applied to your next ticket purchase!\n\n" +
                "Simply enter the following code on your ticket purchase: " + id + "\n\n" +
                "Please note that this code will expire on " + dateFormat.format(expiryDate) + "\n\n" +
                "See you on the big screen!\n\n" +
                "Ticket Kings Inc.";
    }

    private String createNoCreditEmailBody(User user) {
        return user.getName() + ",\n\n" +
                "This is a confirmation that your ticket has been cancelled.\n\n" +
                "Unfortunately, we are unable to issue you a credit as the cancellation was too late.\n\n" +
                "Please consider signing up as a registered user to receive full refunds on cancellations!\n\n" +
                "Ticket Kings Inc.";
    }

}
