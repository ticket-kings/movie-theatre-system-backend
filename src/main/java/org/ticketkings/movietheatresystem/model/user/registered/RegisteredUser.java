package org.ticketkings.movietheatresystem.model.user.registered;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.user.User;

import javax.persistence.*;

/**
 * Represents the registered user entity
 */
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
}
