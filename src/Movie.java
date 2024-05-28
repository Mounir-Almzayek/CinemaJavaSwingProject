import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Movie class represents information about a movie, including its attributes like ID, name, description, etc.
 */
class Movie implements Serializable {
    private int id, year;
    private double price;
    private int views;
    private String name, description, duration, genre, poster;
    private ArrayList<Comment> comments;
    private Map<String, Double> userRatings;
    private ArrayList<Showtime> showtimes;

    public Movie(int id, String name, int year, String description, String duration, String genre, String poster, ArrayList<Showtime> showtime, double price) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.poster = poster;
        this.duration = duration;
        this.price = price;
        this.showtimes = showtime;
        this.views = 0;
        this.comments = new ArrayList<>();
        this.userRatings = new HashMap<>();
    }

    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setShowtimes(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Map<String, Double> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(Map<String, Double> userRatings) {
        this.userRatings = userRatings;
    }

    public void addUserRating(String username, double rating) {
        // Read movie data from file
        HashMap<Integer, Movie> map = readFile();

        // Update user ratings and write back to file
        Map<String, Double> ratings = getUserRatings();
        userRatings.put(username, rating);
        this.setUserRatings(ratings);
        map.put(this.id, this);

        // Try-with-resources to automatically close streams
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("cinema.txt")))) {
            oos.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getUserRate(User user){
        // Read movie data from file
        Map<String, Double> ratings = getUserRatings();
        return ratings.get(user.getUserName());
    }

    public double calculateAverageRating() {
        if (userRatings.isEmpty() || userRatings == null) {
            return 0.0; // Or you can return another suitable value
        }
        double sum = 0.0;
        for (double rating : userRatings.values()) {
            sum += rating;
        }

        return sum / userRatings.size();
    }

    static public HashMap<Integer, Movie> readFile() {
        try (FileInputStream fis = new FileInputStream(new File("cinema.txt"));
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (HashMap<Integer, Movie>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static public HashMap<Integer, Movie> search(String name) {
        // Read movie data from file
        HashMap<Integer, Movie> map = null;
        map = readFile();

        HashMap<Integer, Movie> map2 = new HashMap<>();
        // Search for movies by name
        map.forEach((key, value) -> {
            if (value.getName().contains(name)) {
                map2.put(key, value);
            }
        });
        return map2;
    }

    static public HashMap<Integer, Movie> genre(String genre) {
        // Read movie data from file
        HashMap<Integer, Movie> map = null;
        map = readFile();
        if (genre.equals("All"))
            return map;

        HashMap<Integer, Movie> map2 = new HashMap<>();
        // Filter movies by genre
        map.forEach((key, value) -> {
            if (value.getGenre().equals(genre)) {
                map2.put(key, value);
            }
        });
        return map2;
    }

    public void addComment(Comment comment) {
        // Read movie data from file
        HashMap<Integer, Movie> map = readFile();

        // Add a new comment to the movie
        ArrayList<Comment> comments = this.getComments();
        comments.add(comment);
        this.setComments(comments);
        map.put(this.id, this);

        // Try-with-resources to automatically close streams
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("cinema.txt")))) {
            oos.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void addData(Movie movie) {
        // Read movie data from file
        Map<Integer, Movie> map = readFile();

        // Add a new movie to the map
        map.put(movie.getId(), movie);

        // Write the updated map back to the file
        try (FileOutputStream fileOut = new FileOutputStream("cinema.txt");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public Movie findMovie(Movie movie) {
        // Read movie data from file
        HashMap<Integer, Movie> map = readFile();

        // Find and return the movie
        return map.get(movie.getId());
    }
}
