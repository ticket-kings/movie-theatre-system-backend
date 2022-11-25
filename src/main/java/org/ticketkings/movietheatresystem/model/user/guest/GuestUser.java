package org.ticketkings.movietheatresystem.model.user.guest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="guest_user")
public class GuestUser {

    @Id
    private Integer userId;
    private String name;
    private String emailAddress;
    private int credit;

    public GuestUser(Integer userId, String name, String emailAddress, Integer credit) {
        this.userId = userId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.credit = credit;
    }

    public GuestUser() {
    }
}