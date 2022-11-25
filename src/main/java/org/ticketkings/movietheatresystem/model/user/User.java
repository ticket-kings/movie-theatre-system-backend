package org.ticketkings.movietheatresystem.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user")
public abstract class User {
    @Id
    @JsonManagedReference
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="credit_id")
    private Integer creditId;

    @Column(name="card_id")
    private Integer cardId;

    public User() {
    }

    public User(Integer id, String name, String emailAddress, Integer creditId, Integer cardId) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.creditId = creditId;
        this.cardId = cardId;
    }

}
