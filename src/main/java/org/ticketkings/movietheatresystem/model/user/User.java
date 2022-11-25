package org.ticketkings.movietheatresystem.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="credit_id")
    private Integer creditId;

    @Column(name="card_id")
    private Integer card_id;

    public User() {
    }

    public User(Integer id, String name, String emailAddress, Integer creditId, Integer card_id) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.creditId = creditId;
        this.card_id = card_id;
    }

}
