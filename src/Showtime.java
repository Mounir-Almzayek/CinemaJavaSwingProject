import java.io.Serializable;
import java.time.LocalDate;

/**
 * The Showtime class represents a specific showing of a movie in a cinema, including the date, time interval, and seat availability.
 */
public class Showtime implements Serializable {
    private int cinema_id;
    private LocalDate date;
    private Interval interval;
    private boolean[][] seats;

    public Showtime(int cinema_id, LocalDate date, Interval interval) {
        this.cinema_id = cinema_id;
        this.date = date;
        this.interval = interval;
        this.seats = new boolean[5][11]; // Assuming a cinema layout of 4 rows and 10 columns for seats
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public boolean[][] getSeats() {
        return seats;
    }

    public void setSeats(boolean[][] seats) {
        this.seats = seats;
    }
}
