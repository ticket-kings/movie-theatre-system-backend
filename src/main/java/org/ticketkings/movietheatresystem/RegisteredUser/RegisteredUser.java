package org.ticketkings.movietheatresystem.RegisteredUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registered_user")
public class RegisteredUser {

    @Id

    private String name, phoneNumber, emailAddress, password, billingInformation;

    public RegisteredUser(String name, String phoneNumber, String emailAddress, String password,
            String billingInformation) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.billingInformation = billingInformation;
    }

    @Override
    public String toString() {
        return "RegisteredUser [name=" + name + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress
                + ", password=" + password + ", billingInformation=" + billingInformation + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBillingInformation() {
        return billingInformation;
    }

    public void setBillingInformation(String billingInformation) {
        this.billingInformation = billingInformation;
    }
}
