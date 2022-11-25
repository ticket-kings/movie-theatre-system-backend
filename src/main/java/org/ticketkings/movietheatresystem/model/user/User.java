package org.ticketkings.movietheatresystem.model.user;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    Integer id;

    @Column(name="name")
    String name;

    @Column(name="email_address")
    String emailAddress;

    @Column(name="credit_id")
    Integer creditId;

    @Column(name="card_id")
    Integer card_id;

}
