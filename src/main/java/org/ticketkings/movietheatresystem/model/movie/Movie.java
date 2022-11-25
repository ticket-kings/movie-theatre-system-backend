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
    private Integer id;
    private String name;

    private String imageUrl;
    private String description;
    private Integer duration;

    public Movie(Integer id, String name, String imageUrl, String description, Integer duration) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.duration = duration;
    }

    public Movie() {
    }
}
