package org.ticketkings.movietheatresystem.model.credit;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.ticketkings.movietheatresystem.model.seat.Seat;
import org.ticketkings.movietheatresystem.model.showtime.Showtime;
import org.ticketkings.movietheatresystem.model.ticket.Ticket;
import org.ticketkings.movietheatresystem.model.user.User;
import org.ticketkings.movietheatresystem.model.user.guest.GuestUser;
import org.ticketkings.movietheatresystem.model.user.registered.RegisteredUser;

@Entity
@Getter
@Setter
@Table(name = "credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "expired")
    private Boolean expired;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @OneToOne(mappedBy = "credit")
    @JsonBackReference
    private Ticket ticket;

    public Credit(int id, String code, float amount, Boolean expired, Date expiryDate) {
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.expired = expired;
        this.expiryDate = expiryDate;
    }

    public Credit() {
    }

    public void expireCredit() {
        expired = true;
    }

    public Float apply(Float price) {
        float newPrice = price - amount;
        float newAmount = amount - price;
        amount = newAmount < 0 ? 0 : newAmount;
        return newPrice < 0 ? 0 : newPrice;
    }

    public void calculate(User user, Showtime showtime, Seat seat) {
        Date now = new Date();
        Date time = showtime.getTime();
        if (now.after(time)) {
            amount = (float) 0;
            return;
        }

        if (user instanceof RegisteredUser)
            amount = seat.getPrice();
        else if (user instanceof GuestUser && isMoreThan3DaysFromNow(time))
            amount = seat.getPrice() * (float) 0.85;
        else {
            amount = (float) 0;
            return;
        }

        expiryDate = oneYearFromNow();
        code = generateCode();
        expired = false;
    }

    private boolean isMoreThan3DaysFromNow(Date time) {
        Date now = new Date();
        long milli = time.getTime() - now.getTime();
        long hours = milli / (3600 * 1000);
        return hours >= 72;
    }

    private Date oneYearFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }

    private String generateCode() {
        String letters = "ABCDEFGHJKLMNPQRSTUVWXYZ";
        String digits = "123456789";
        String symbols = letters + digits;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = (int)(symbols.length() * Math.random());
            sb.append(symbols.charAt(index));
        }

        return sb.toString();
    }

}
