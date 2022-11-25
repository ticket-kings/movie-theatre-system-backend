package org.ticketkings.movietheatresystem.model.showtime;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.showing.Showing;

@Entity
@Getter
@Setter
@Table(name = "showtime")
public class Showtime {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "time")
    private Date time;

    @JsonManagedReference
    @OneToMany(mappedBy = "showtime")
    private List<Seat> seats;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "showing", insertable = false, updatable = false)
    private Showing showing;

    public Showtime(Integer id, Date time) {
        this.id = id;
        this.time = time;
    }

    public Showtime() {
    }
}
