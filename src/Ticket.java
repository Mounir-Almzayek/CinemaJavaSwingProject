import java.io.*;
import java.time.LocalDate;

/**
 * This class represents a ticket in the application.
 * Users can purchase tickets for movies with this class.
 */
public class Ticket implements Serializable {

    private int id;
    private int movie_id;
    private int cinema_id;
    private double price;
    private Pair<Integer, Integer> seat;
    private Showtime showtime;
    private String payment;
    private int indexOfShowtime;

    public Ticket(int movie_id, double price, int cinema_id, Pair<Integer, Integer> seat, String payment, Showtime showtime, int indexOfShowtime) {
        this.movie_id = movie_id;
        this.cinema_id = cinema_id;
        this.price = price;
        this.seat = seat;
        this.payment = payment;
        this.id = getLastId();
        this.showtime = showtime;
        this.indexOfShowtime = indexOfShowtime;
        setLastId(this.id + 1);
    }

    public int getIndexOfShowtime() {
        return indexOfShowtime;
    }

    public void setIndexOfShowtime(int indexOfShowtime) {
        this.indexOfShowtime = indexOfShowtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Pair<Integer, Integer> getSeat() {
        return seat;
    }

    public void setSeat(Pair<Integer, Integer> seat) {
        this.seat = seat;
    }

    private static int getLastId() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("lastId.txt"));
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            } else {
                return 1;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setLastId(int i) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("lastId.txt"));
            writer.write(Integer.toString(i));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
