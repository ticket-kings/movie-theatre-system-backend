package org.ticketkings.movietheatresystem.model.user.registered;

import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="registered_user")
public class RegisteredUser extends User {

    @PrimaryKeyJoinColumn(name="id", referencedColumnName="userId")
    @Column(name="user_id")
    private String userId;

    @Column(name="address")
    private String address;

    @Column(name="password")
    private String password;

    public RegisteredUser(String address, String password) {
        this.address = address;
        this.password = password;
    }

    public RegisteredUser() {
    }
}
