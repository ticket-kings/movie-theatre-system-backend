package org.ticketkings.movietheatresystem.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.ticketkings.movietheatresystem.model.credit.Credit;
import org.ticketkings.movietheatresystem.model.ticket.Ticket;
import org.ticketkings.movietheatresystem.model.user.User;
import org.ticketkings.movietheatresystem.model.user.registered.RegisteredUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy h:mm aa z");

    @Value("${spring.mail.username}") private String sender;

    public void sendEmail(EmailDetails details)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());

        javaMailSender.send(mailMessage);
    }

    public void sendRegistrationConfirmationEmail(RegisteredUser user) {
        sendEmail(new EmailDetails(
            user.getEmailAddress(),
            "Your account has been created!",
            user.getName() + ",\n\n" +
                    "Thank you for registering for the Ticket Kings Movie Theatre System!\n\n" +
                    "Please confirm your details below:\n\n" +
                    "Name: " + user.getName() + "\n" +
                    "Address: " + user.getAddress() + "\n" +
                    "Card:\n" +
                    "\tNumber: " + user.getCard().getCardNumber() + "\n" +
                    "\tExpiry Date: " + user.getCard().getExpiryDate() + "\n" +
                    "\tCVV: " + user.getCard().getCvv() + "\n\n" +
                    "See you on the big screen!\n\n" +
                    "Ticket Kings Inc.")
        );
    }

    public void sendTicketPurchasedEmail(User user, Ticket ticket) {
        sendEmail(new EmailDetails(
                    user.getEmailAddress(),
                    "Your ticket has been reserved!",
                    createTicketPurchasedEmailBody(user, ticket)
            )
        );
    }

    private String createTicketPurchasedEmailBody(User user, Ticket ticket) {
        return user.getName() + ",\n\n" +
                "This is a confirmation that your ticket has been reserved!\n\n" +
                "Here is your showtime information and e-receipt:\n\n" +
                "Movie:\n" +
                "\tName: " + ticket.getShowing().getMovie().getName() + "\n" +
                "\tDescription: " + ticket.getShowing().getMovie().getDescription() + "\n" +
                "\tDuration: " + ticket.getShowing().getMovie().getDuration() + " minutes\n\n" +
                "Showtime:\n" +
                "\tTime: " + dateFormat.format(ticket.getShowing().getShowtime().getTime()) + "\n" +
                "\tSeat: " + ticket.getSeat().getSeatNumber() + "\n\n" +
                "Payment Information:\n" +
                "\tSeat Price: $" + String.format("%.2f", ticket.getSeat().getPrice()) + "\n" +
                "\tCredit Applied: " + getCreditApplied(ticket) + "\n" +
                "\tAmount Paid: $" + String.format("%.2f", ticket.getPayment().getAmount()) + "\n" +
                "\tPayment Date: " + dateFormat.format(ticket.getPayment().getPaymentDate()) + "\n\n" +
                "Credit Card:\n" +
                "\tNumber: " + user.getCard().getCardNumber() + "\n" +
                "\tExpiry Date: " + user.getCard().getExpiryDate() + "\n" +
                "\tCVV: " + user.getCard().getCvv() + "\n\n" +
                getCancellationNotice(user) +
                "See you on the big screen!\n\n" +
                "Ticket Kings Inc.";
    }

    private String getCreditApplied(Ticket ticket) {
        if (Objects.equals(ticket.getPayment().getAmount(), ticket.getSeat().getPrice())) return "N/A";

        return "-$" + String.format("%.2f", ticket.getSeat().getPrice() - ticket.getPayment().getAmount());
    }

    private String getCancellationNotice(User user) {
        if (user instanceof RegisteredUser)
            return "As you are a registered user, you may cancel this ticket at any time to receive a full credit refund!\n\n";
        else
            return "As you are a guest user, you may receive a credit with 15% administration fee if you cancel this ticket 72 hours before showtime. " +
                    "Please note that you will not receive any credit if you cancel less than 72 hours before showtime.\n\n";
    }

    public void createCreditEmail(User user, Credit credit) {
        sendEmail(new EmailDetails(
                user.getEmailAddress(),
                "Ticket Cancellation Confirmation",
                credit.getAmount() == 0 ? createNoCreditEmailBody(user) : createCreditEmailBody(user, credit)
            )
        );
    }

    private String createCreditEmailBody(User user, Credit credit) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy h:mm aa z");
        return user.getName() + ",\n\n" +
                "This is a confirmation that your ticket has been cancelled.\n\n" +
                "You have received a credit of $" + String.format("%.2f", credit.getAmount()) + " that can be applied to your next ticket purchase!\n\n" +
                "Simply enter the following code on your ticket purchase: " + "\n\n" +
                credit.getCode() + "\n\n" +
                "Please note that this code will expire on " + dateFormat.format(credit.getExpiryDate()) + "\n\n" +
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
