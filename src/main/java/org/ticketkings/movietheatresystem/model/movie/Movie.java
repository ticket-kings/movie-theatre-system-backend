package org.ticketkings.movietheatresystem.model.movie;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {

    @Id

    private String name, description;
    private int duration, id;

    public Movie(String name, String description, int duration, int id) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movie [name=" + name + ", description=" + description + ", duration=" + duration + ", id=" + id + "]";
    }
}
