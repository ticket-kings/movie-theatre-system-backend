package org.ticketkings.movietheatresystem.model.Theatre;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @Column(name = "name")
    private String name;

    @Column(name = "Id")
    private int id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "showing", insertable = false, updatable = false)
    private Showing showing;

    public Theatre(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Theatre() {

    }
}
