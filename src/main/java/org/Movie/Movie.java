package org.Movie;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
