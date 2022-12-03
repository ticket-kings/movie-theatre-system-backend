package org.ticketkings.movietheatresystem.model.user.registered;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.email.EmailDetails;
import org.ticketkings.movietheatresystem.email.EmailService;
import org.ticketkings.movietheatresystem.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name="user_id")
@Table(name="registered_user")
public class RegisteredUser extends User {

    @JsonBackReference
    @Column(name="user_id", insertable = false, updatable = false)
    private Integer userId;

    @Column(name="address")
    private String address;

    @Column(name="password")
    private String password;

    public RegisteredUser(Integer userId, String address, String password) {
        this.userId = userId;
        this.address = address;
        this.password = password;
    }

    public RegisteredUser() {
    }

    public void sendRegistrationConfirmationEmail(EmailService emailService) {
        emailService.sendEmail(new EmailDetails(
                getEmailAddress(),
                "Your account has been created!",
                getName() + ",\n\n" +
                        "Thank you for registering for the Ticket Kings Movie Theatre System!\n\n" +
                        "Please confirm your details below:\n\n" +
                        "Name: " + getName() + "\n" +
                        "Address: " + getAddress() + "\n" +
                        "Card:\n" +
                        "\tNumber: " + getCard().getCardNumber() + "\n" +
                        "\tExpiry Date: " + getCard().getExpiryDate() + "\n" +
                        "\tCVV: " + getCard().getCvv() + "\n\n" +
                        "See you on the big screen!\n\n" +
                        "Ticket Kings Inc.")
        );
    }
}
