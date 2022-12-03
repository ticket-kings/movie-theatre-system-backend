package org.ticketkings.movietheatresystem.model.ticket;

import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.email.EmailDetails;
import org.ticketkings.movietheatresystem.email.EmailService;
import org.ticketkings.movietheatresystem.model.credit.Credit;
import org.ticketkings.movietheatresystem.model.payment.ticket.TicketPayment;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.showing.Showing;
import org.ticketkings.movietheatresystem.model.user.User;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="payment_id")
    private Integer paymentId;

    @Column(name="seat_id")
    private Integer seatId;

    @Column(name="showing_id")
    private Integer showingId;

    @Column(name="credit_id")
    private Integer creditId;

    @OneToOne
    @JoinColumn(name = "payment_id", insertable = false, updatable = false)
    private TicketPayment payment;

    @OneToOne
    @JoinColumn(name = "seat_id", insertable = false, updatable = false)
    private Seat seat;

    @OneToOne
    @JoinColumn(name = "showing_id", insertable = false, updatable = false)
    private Showing showing;

    @OneToOne
    @JoinColumn(name = "credit_id", insertable = false, updatable = false)
    private Credit credit;

    public Ticket() {
    }

    public Ticket(Integer id, Integer paymentId, Integer seatId, Integer creditId) {
        this.id = id;
        this.paymentId = paymentId;
        this.seatId = seatId;
        this.creditId = creditId;
    }

    public void sendTicketPurchasedEmail(User user, EmailService emailService) {
        emailService.sendEmail(new EmailDetails(
                user.getEmailAddress(),
                "Your ticket has been reserved!",
                createTicketPurchasedEmailBody(user)
            )
        );
    }

    private String createTicketPurchasedEmailBody(User user) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy h:mm aa z");
        return user.getName() + ",\n\n" +
                "This is a confirmation that your ticket has been reserved!\n\n" +
                "Your showtime information and receipt is shown below:\n\n" +
                "Movie:\n" +
                "\tName: " + getShowing().getMovie().getName() + "\n" +
                "\tDescription: " + getShowing().getMovie().getDescription() + "\n" +
                "\tDuration: " + getShowing().getMovie().getDuration() + " minutes\n\n" +
                "Showtime:\n" +
                "\tTime: " + dateFormat.format(getShowing().getShowtime().getTime()) + "\n" +
                "\tSeat: " + getSeat().getSeatNumber() + "\n\n" +
                "Payment Information:\n" +
                "\tSeat Price: $" + String.format("%.2f", getSeat().getPrice()) + "\n" +
                "\tCredit Applied: " + getCreditApplied() + "\n" +
                "\tAmount Paid: $" + String.format("%.2f", getPayment().getAmount()) + "\n" +
                "\tPayment Date: " + dateFormat.format(getPayment().getPaymentDate()) + "\n\n" +
                "Credit Card:\n" +
                "\tNumber: " + user.getCard().getCardNumber() + "\n" +
                "\tExpiry Date: " + user.getCard().getExpiryDate() + "\n" +
                "\tCVV: " + user.getCard().getCvv() + "\n\n" +
                "See you on the big screen!\n\n" +
                "Ticket Kings Inc.";
    }

    private String getCreditApplied() {
        if (Objects.equals(payment.getAmount(), seat.getPrice())) return "N/A";

        return "-$" + String.format("%.2f", seat.getPrice() - payment.getAmount());
    }
}
