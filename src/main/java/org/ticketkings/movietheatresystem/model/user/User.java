package org.ticketkings.movietheatresystem.model.user;

import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.card.Card;

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

    @Column(name="card_id")
    private Integer cardId;

    @OneToOne
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    private Card card;

    public User() {
    }

    public User(Integer id, String name, String emailAddress, Integer cardId) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.cardId = cardId;
    }

}
