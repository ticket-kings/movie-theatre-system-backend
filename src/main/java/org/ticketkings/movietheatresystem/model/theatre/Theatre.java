package org.ticketkings.movietheatresystem.model.theatre;

import javax.persistence.*;

import org.ticketkings.movietheatresystem.model.showing.Showing;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "theatre")
public class Theatre {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "showing", insertable = false, updatable = false)
    private Showing showing;

    public Theatre(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public Theatre() {

    }
}
