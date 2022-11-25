package org.ticketkings.movietheatresystem.model.user.guest;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name="user_id")
@Table(name="guest_user")
public class GuestUser extends User {

    @JsonBackReference
    @Column(name="user_id", insertable = false, updatable = false)
    private Integer userId;

    public GuestUser(Integer userId) {
        this.userId = userId;
    }

    public GuestUser() {
    }
}