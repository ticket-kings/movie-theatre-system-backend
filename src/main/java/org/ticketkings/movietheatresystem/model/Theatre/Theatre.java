package org.ticketkings.movietheatresystem.model.Theatre;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "theatre")
public class Theatre {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "Id")
    private int id;

    public Theatre(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Theatre() {

    }
}
