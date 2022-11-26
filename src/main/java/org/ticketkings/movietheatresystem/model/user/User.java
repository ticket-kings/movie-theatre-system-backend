package org.ticketkings.movietheatresystem.model.user;

import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.credit.Credit;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="user")
public abstract class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="credit_id")
    private Integer creditId;

    @OneToOne
    @JoinColumn(name = "credit_id", insertable = false, updatable = false)
    private Credit credit;

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
