import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class handles the booking and cancellation of tickets in the application.
 * It interacts with user and movie data to update ticket information.
 */
public class Ticketing {


    static public void bookTicket(User user, Ticket ticket, int indexForTime) {
        Map<String, User> map = User.readFile();
        user.getTickets().add(ticket);

        Map<Integer, Movie> map2 = null;
        map2 = Movie.readFile();
        Movie movie = map2.get(ticket.getMovie_id());
        movie.setViews(movie.getViews()+1);
        boolean array[][] = movie.getShowtimes().get(indexForTime).getSeats();
        array[ticket.getSeat().getFirst()][ticket.getSeat().getSecond()] = true;
        movie.getShowtimes().get(indexForTime).setSeats(array);
        map2.put(movie.getId(), movie);

        File file2 = new File("cinema.txt");
        FileOutputStream fos2 = null;
        try {
            fos2 = new FileOutputStream(file2);
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(map2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        map.put(user.getUserName(), user);
        try {
            File file = new File("users.txt");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public boolean cancelTicket(User user, Ticket oldTicket) {
        ArrayList<Ticket> tickets = user.getTickets();
        int showtimeIndex = -1;
        Ticket currTicket = null;

        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            if (oldTicket == null)
                return false;
            if (ticket.getId() == oldTicket.getId()) {
                showtimeIndex = ticket.getIndexOfShowtime();
                currTicket = ticket;
                tickets.remove(i);
                break;
            }
        }

        user.setTickets(tickets);
        User.editData(user);

        HashMap<Integer, Movie> movies = Movie.readFile();
        if (oldTicket == null)
            return false;
        Movie movie = movies.get(oldTicket.getMovie_id());

        // Cancel the reservation in the seat list for the showtime
        boolean[][] seats = movie.getShowtimes().get(showtimeIndex).getSeats();
        seats[currTicket.getSeat().getFirst()][currTicket.getSeat().getSecond()] = false;

        // Save the changes in the movies file
        Movie.addData(movie);
        return true;
    }
}
