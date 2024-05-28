import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a user in the application.
 */
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private ArrayList<Ticket> tickets;
    private String gender;

    public User(String firstName, String lastName, String userName, String password, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.tickets = new ArrayList<Ticket>();
    }

    public User() {
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    static public Map<String, User> readFile() {
        Map<String, User> map = new HashMap<>();
        try {
            File f = new File("users.txt");
            FileInputStream fileIn = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            map = (Map<String, User>) in.readObject();
            in.close();
            fileIn.close();
        } catch (EOFException e) {
            // File is empty, return an empty map
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    static boolean addData(User user) {
        Map<String, User> map = readFile();
        if (map.containsKey(user.getUserName())) {
            return false;
        } else {
            map.put(user.getUserName(), user);
            try {
                File f = new File("users.txt");
                FileOutputStream fileOut = new FileOutputStream(f);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(map);
                out.close();
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    static void editData(User user) {
        Map<String, User> map = readFile();
        map.put(user.getUserName(), user);
        try {
            File f = new File("users.txt");
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and setters for user attributes

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    User login() {
        Map<String, User> map = readFile();

        if (map.containsKey(this.userName) && map.get(this.userName).password.equals(this.password)) {
            return map.get(this.userName);
        }
        return null;
    }

    boolean editData(String username) {
        Map<String, User> map = readFile();
        if (username.equals(this.userName)) {
            map.put(this.userName, this);
        } else {
            if (map.containsKey(this.userName)) {
                return false;
            } else {
                map.remove(username);
                map.put(this.userName, this);
            }
        }
        try {
            File f = new File("users.txt");
            FileOutputStream fileOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Ticket getTicketById(int ticketId) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == ticketId) {
                return ticket;
            }
        }
        return null;
    }
}
