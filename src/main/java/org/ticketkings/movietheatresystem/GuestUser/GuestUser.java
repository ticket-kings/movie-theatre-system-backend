package org.ticketkings.movietheatresystem.GuestUser;

public class GuestUser {

    private String name, emailAddress;

    public GuestUser(String name, String emailAddress, int credit) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    private int credit;
}