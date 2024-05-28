import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.*;

/**
 * This class represents the Coming Soon panel in the application.
 */
public class ComingSoon extends JPanel {
    private int scrollPosition = 0;
    private int scrollDirection = 1;
    private Timer scrollTimer;
    private boolean manualScrolling = false;

    public ComingSoon(User user) {
        setLayout(null);

        // Container for displaying movies
        JPanel moviesPanelContainer = new JPanel();
        moviesPanelContainer.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Retrieve movie data from file and sort by showtime date
        HashMap<Integer, Movie> movies = null;
        movies = (HashMap<Integer, Movie>) Movie.readFile();

        ArrayList<Movie> movieList = new ArrayList<>(movies.values());
        Collections.sort(movieList, Comparator.comparing(m -> m.getShowtimes().get(0).getDate()));

        // Get unique movie IDs and select the first 12 movies
        ArrayList<Movie> nearestMovies = new ArrayList<>();
        Set<Integer> uniqueMovieIds = new HashSet<>();

        for (Movie movie : movieList) {
            if (uniqueMovieIds.add(movie.getId())) {
                nearestMovies.add(movie);
            }
            if (nearestMovies.size() == 12) {
                break;
            }
        }

        // Create MoviePanel for each movie and add to the container
        for (Movie movie : nearestMovies) {
            MoviePanel moviePanel = new MoviePanel(user, movie, "ComingSoon");
            moviesPanelContainer.add(moviePanel);
        }

        // Create a scroll pane for the movies container
        JScrollPane scrollPane = new JScrollPane(moviesPanelContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(90, 170, 950, 500);
        add(scrollPane);

        // Add a label indicating "Coming soon"
        JLabel mostPopularLabel = new JLabel("<html><font color='#EBC548FF'>|</font> " + " Coming soon");
        mostPopularLabel.setFont(new Font("Arial", Font.BOLD, 35));
        mostPopularLabel.setForeground(new Color(69, 71, 75));
        mostPopularLabel.setBounds(50, 70, 400, 50);
        add(mostPopularLabel);

        // Add a back button to navigate to the "about" panel
        ImageIcon backImage = new ImageIcon("icons\\icons8-back-32.png");
        JButton backButton = new JButton();
        backButton.setLayout(new GridLayout(1, 3));
        JLabel backLabel = new JLabel("Back");
        backLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        backLabel.setForeground(Color.white);
        backButton.add(new JLabel(backImage));
        backButton.add(backLabel);
        backButton.add(new JLabel());
        backButton.setBounds(990, 50, 150, 50);
        backButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        backButton.setBackground(new Color(0x2f2f2f));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) HomePanel.cardPanel.getLayout();
                cardLayout.show(HomePanel.cardPanel, "about");
            }
        });
        add(backButton);

        // Add left scroll button
        JButton scrollLeftButton = new JButton("<") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 20;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
                if (getModel().isArmed()) {
                    g2.setColor(new Color(69, 71, 75));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(235, 197, 72));
                } else {
                    g2.setColor(new Color(69, 71, 75));
                }
                g2.fill(roundRect);
                g2.setColor(Color.white);
                g2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
                FontMetrics fm = g.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();
                g2.drawString(text, x, y);
                g2.dispose();
            }
        };
        scrollLeftButton.setBorderPainted(false);
        scrollLeftButton.setFocusPainted(false);
        scrollLeftButton.setContentAreaFilled(false);
        scrollLeftButton.setBorder(null);
        scrollLeftButton.setBounds(50, 355, 30, 125);
        scrollLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
                horizontalScrollBar.setValue(horizontalScrollBar.getValue() - horizontalScrollBar.getBlockIncrement(-1));
            }
        });
        add(scrollLeftButton);

        // Add right scroll button
        JButton scrollRightButton = new JButton(">") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 20;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
                if (getModel().isArmed()) {
                    g2.setColor(new Color(69, 71, 75));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(235, 197, 72));
                } else {
                    g2.setColor(new Color(69, 71, 75));
                }
                g2.fill(roundRect);
                g2.setColor(Color.white);
                g2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
                FontMetrics fm = g.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();
                g2.drawString(text, x, y);
                g2.dispose();
            }
        };
        scrollRightButton.setBorderPainted(false);
        scrollRightButton.setFocusPainted(false);
        scrollRightButton.setContentAreaFilled(false);
        scrollRightButton.setBorder(null);
        scrollRightButton.setBounds(1050, 355, 30, 125);
        scrollRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
                horizontalScrollBar.setValue(horizontalScrollBar.getValue() + horizontalScrollBar.getBlockIncrement(1));
            }
        });
        add(scrollRightButton);

        // Initialize and start the automatic scroll timer
        scrollTimer = new Timer(4000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
                int increment = horizontalScrollBar.getBlockIncrement(scrollDirection);
                int targetPosition = scrollPosition + increment * scrollDirection;
                int steps = 50;
                int delay = 10;
                int stepIncrement = (targetPosition - scrollPosition) / steps;
                Timer scrollStepTimer = new Timer(delay, null);
                scrollStepTimer.addActionListener(new ActionListener() {
                    int currentStep = 0;
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        scrollPosition += stepIncrement;
                        horizontalScrollBar.setValue(scrollPosition);
                        currentStep++;

                        if (currentStep >= steps) {
                            scrollStepTimer.stop();
                            if (scrollPosition >= horizontalScrollBar.getMaximum()) {
                                scrollPosition = 0;
                                horizontalScrollBar.setValue(scrollPosition);
                            }
                        }
                    }
                });
                scrollStepTimer.start();
            }
        });
        scrollTimer.start();
    }
}
