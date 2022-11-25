package org.ticketkings.movietheatresystem.model.movie;

import javax.persistence.*;

import org.ticketkings.movietheatresystem.model.showing.Showing;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "is_released")
    private Boolean isReleased;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "showing", insertable = false, updatable = false)
    private Showing showing;

    public Movie(Integer id, String name, String imageUrl, String description, Integer duration, Boolean isReleased) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.duration = duration;
        this.isReleased = isReleased;
    }

    public Movie() {
    }
}
