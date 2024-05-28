import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * The MoviesPanel class represents a panel that displays a list of movies, including a search field, search button, genre filter, and movie panels.
 */
class MoviesPanel extends JPanel {
    private JTextField searchField;
    private JComboBox<String> genreComboBox;
    private JPanel moviesPanelContainer;

    public MoviesPanel(User user) {
        setLayout(null);
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 1150, 50);

        // Search field for searching movies
        searchField = new JTextField(50);
        searchField.setBackground(new Color(0xF2F1EB));
        searchField.setBounds(235, 15, 600, 35);
        searchField.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.darkGray));
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        topPanel.add(searchField);
        searchField.setText("Search...");
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search...")) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search...");
                }
            }
        });
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Perform search when Enter key is pressed
                    String search = searchField.getText();
                    if (!search.isEmpty()) {
                        updateMoviesPanel(Movie.search(search), user);
                    } else {
                        updateMoviesPanel(Movie.readFile(), user);
                    }
                }
            }
        });

        // Search button
        JButton searchButton = new JButton(new ImageIcon("icons\\icons8-magnifier-32.png"));
        searchButton.setBounds(200, 15, 35, 35);
        searchButton.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.darkGray));
        searchButton.setBackground(new Color(0xF2F1EB));
        topPanel.add(searchButton);

        // Genre filter combo box
        genreComboBox = new JComboBox<>(new String[]{"All", "Action", "Drama", "Comedy", "Sci-Fi", "Fantasy"});
        genreComboBox.setBackground(new Color(0xF2F1EB));
        genreComboBox.setBounds(835, 15, 100, 36);
        genreComboBox.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(genreComboBox);

        add(topPanel);

        // Container for movie panels
        moviesPanelContainer = new JPanel(new GridLayout(0, 4, 15, 15));
        JScrollPane scrollPane = new JScrollPane(moviesPanelContainer);
        scrollPane.setBounds(20, 65, 1100, 700);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setBlockIncrement(64);
        add(scrollPane);

        // Display initial list of movies
        updateMoviesPanel(Movie.readFile(), user);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                if (!search.isEmpty()) {
                    updateMoviesPanel(Movie.search(search), user);
                } else {
                    updateMoviesPanel(Movie.readFile(), user);
                }
            }
        });

        // Action listener for genre filter combo box
        genreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genre = (String) genreComboBox.getSelectedItem();
                updateMoviesPanel(Movie.genre(genre), user);
            }
        });
    }

    /**
     * Update the movies panel with the given movies.
     *
     * @param movies The movies to display in the panel.
     * @param user
     */
    private void updateMoviesPanel(HashMap<Integer, Movie> movies, User user) {
        moviesPanelContainer.removeAll();
        moviesPanelContainer.revalidate();
        moviesPanelContainer.repaint();

        movies.forEach((key, value) -> {
            MoviePanel moviePanel = new MoviePanel(user, value, "movies");
            moviesPanelContainer.add(moviePanel);
        });

        moviesPanelContainer.revalidate();
        moviesPanelContainer.repaint();
    }
}
