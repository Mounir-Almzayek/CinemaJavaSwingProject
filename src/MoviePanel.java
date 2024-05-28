import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

/**
 * The MoviePanel class represents a panel that displays information about a movie, including its poster, name, average rating, and a "Show more" button.
 */
class MoviePanel extends JPanel {

    public MoviePanel(User user, Movie movie, String howCall) {
        setLayout(new GridBagLayout());
        setBackground(new Color(0x2f2f2f));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Display movie poster
        ImageIcon poster = new ImageIcon(movie.getPoster());
        JLabel posterLabel = new JLabel(poster);
        posterLabel.setHorizontalAlignment(JLabel.CENTER);
        posterLabel.setBackground(new Color(51, 51, 52));
        add(posterLabel, gbc);

        // Display movie name
        gbc.gridy = 2;
        JLabel nameLabel = new JLabel(movie.getName());
        nameLabel.setFont(new Font("Bahnschrift", Font.BOLD, 17));
        nameLabel.setPreferredSize(new Dimension(200, 20));
        nameLabel.setForeground(Color.white);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(nameLabel, gbc);

        // Display movie average rating
        gbc.gridy = 1;
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel ratingLabel = new JLabel(movie.calculateAverageRating() + "");
        ratingLabel.setFont(new Font("Agency FB", Font.BOLD, 18));
        ratingLabel.setForeground(Color.white);
        ratingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon starIcon = new ImageIcon("icons\\icons8-star-24.png");
        JLabel starLabel = new JLabel(starIcon);
        ratingPanel.setBackground(new Color(0x2f2f2f));
        ratingPanel.add(starLabel);
        ratingPanel.add(ratingLabel);
        add(ratingPanel, gbc);

        // "Show more" button to navigate to detailed movie information
        gbc.gridy = 3;
        JButton showMoreButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 10;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
                if (getModel().isArmed()) {
                    g2.setColor(new Color(235, 197, 72));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(235, 197, 72, 204));
                } else {
                    g2.setColor(new Color(235, 197, 72));
                }
                g2.fill(roundRect);
                g2.dispose();
            }
        };
        showMoreButton.setBorderPainted(false);
        showMoreButton.setFocusPainted(false);
        showMoreButton.setContentAreaFilled(false);
        JLabel showMoreLabel = new JLabel("Show more");
        showMoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        showMoreLabel.setVerticalAlignment(SwingConstants.CENTER);
        showMoreLabel.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        Dimension buttonSize = new Dimension(230, 30);
        showMoreButton.setPreferredSize(buttonSize);
        showMoreButton.setLayout(new BorderLayout());
        showMoreButton.add(showMoreLabel, BorderLayout.CENTER);
        add(showMoreButton, gbc);
        showMoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate to the MovieDetailsPanel when the "Show more" button is clicked
                SideBarFrame.cardPanel.add(new MovieDetailsPanel(user, movie, howCall), "movieDetails");
                CardLayout cardLayout = (CardLayout) SideBarFrame.cardPanel.getLayout();
                cardLayout.show(SideBarFrame.cardPanel, "movieDetails");
            }
        });

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ratingLabel.setText(movie.calculateAverageRating() + "");
            }
        });
        timer.start();
    }
}
