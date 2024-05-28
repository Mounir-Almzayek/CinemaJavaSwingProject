import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.*;

/**
 * The MovieDetailsPanel class represents the panel that displays detailed information about a movie,
 * including its name, genre, rating, description, comments, and options for booking tickets.
 */
public class MovieDetailsPanel extends JPanel {
    // Variable to keep track of the last clicked rating button
    private JButton lastClickedButton = null;

    public MovieDetailsPanel(User user, Movie movie, String howCalled) {
        setLayout(null);

        // Back button to navigate to the previous panel
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
                // Navigate back based on how the panel was called
                if (howCalled.equals("movies")) {
                    CardLayout cardLayout = (CardLayout) SideBarFrame.cardPanel.getLayout();
                    cardLayout.show(SideBarFrame.cardPanel, howCalled);
                } else if (howCalled.equals("ComingSoon")) {
                    CardLayout cardLayout = (CardLayout) SideBarFrame.cardPanel.getLayout();
                    cardLayout.addLayoutComponent(new ComingSoon(user), "ComingSoon");
                    cardLayout.show(SideBarFrame.cardPanel, howCalled);
                } else if (howCalled.equals("MostPopular")){
                    CardLayout cardLayout = (CardLayout) SideBarFrame.cardPanel.getLayout();
                    cardLayout.addLayoutComponent(new MostPopular(user), "MostPopular");
                    cardLayout.show(SideBarFrame.cardPanel, howCalled);
                }
            }
        });
        add(backButton);

        // Display movie poster
        ImageIcon poster = new ImageIcon(movie.getPoster());
        JLabel posterLabel = new JLabel(poster);
        posterLabel.setBounds(70, 125, 240, 360);
        add(posterLabel);

        // Display movie name
        JLabel nameLabel = new JLabel("<html><font color='#EBC548FF'>|</font> " + movie.getName() + "</html>");
        nameLabel.setFont(new Font("Hacen Beirut Poster", Font.BOLD, 45));
        nameLabel.setForeground(new Color(69, 71, 75));
        nameLabel.setBounds(50, 60, 1000, 50);
        add(nameLabel);

        // Display movie genre
        JLabel genreLabel = new JLabel("<html>" + movie.getGenre() + "<font color='#EBC548FF'> |</font></html>");
        genreLabel.setBounds(330, 200, 150, 35);
        genreLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(genreLabel);

        // Display movie rating
        JLabel ratingLabel = new JLabel(movie.getUserRate(user) + " / 5");
        ratingLabel.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        ratingLabel.setBounds(470, 215, 200, 20);
        add(ratingLabel);

        // Display movie description
        JTextArea detailsTextArea = new JTextArea(movie.getDescription());
        detailsTextArea.setFont(new Font("Calibri Light", Font.PLAIN, 18));
        detailsTextArea.setForeground(Color.DARK_GRAY);
        detailsTextArea.setBounds(330, 250, 400, 230);
        detailsTextArea.setBackground(new Color(236, 236, 237));
        detailsTextArea.setEditable(false);
        detailsTextArea.setLineWrap(true);
        detailsTextArea.setWrapStyleWord(true);
        add(detailsTextArea);

        // Display comments label
        JLabel commentsLabel = new JLabel("<html><font color='#EBC548FF'>|</font> " + "User review:" + "</html>");
        commentsLabel.setFont(new Font("Arial", Font.BOLD, 28));
        commentsLabel.setForeground(new Color(69, 71, 75));
        commentsLabel.setBounds(70, 500, 1000, 50);
        add(commentsLabel);

        // Text pane to display comments
        JTextPane commentsPane = new JTextPane();
        commentsPane.setEditable(false);
        commentsPane.setBackground(new Color(245, 247, 248));
        commentsPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(235, 197, 72)));
        ArrayList<Comment> comments = movie.getComments();
        StyledDocument styledDocument = commentsPane.getStyledDocument();
        updateCommentsPaneColor(comments, styledDocument);

        JScrollPane scrollPane = new JScrollPane(commentsPane);
        scrollPane.setBounds(80, 550, 650, 120);
        scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(235, 197, 72)));
        add(scrollPane);

        // Text pane for adding new comments
        JTextPane newCommentsPane = new JTextPane();
        newCommentsPane.setEditable(true);
        newCommentsPane.setForeground(Color.WHITE);
        newCommentsPane.setFont(new Font("Arial", Font.PLAIN, 16));
        newCommentsPane.setBackground(new Color(69, 71, 75));
        newCommentsPane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(235, 197, 72)));
        newCommentsPane.setText("Write your comment here");
        newCommentsPane.setBounds(80, 670, 620, 30);
        add(newCommentsPane);
        newCommentsPane.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (newCommentsPane.getText().equals("Write your comment here")) {
                    newCommentsPane.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (newCommentsPane.getText().isEmpty()) {
                    newCommentsPane.setText("Write your comment here");
                }
            }
        });

        // Button to add a new comment
        ImageIcon sendImage = new ImageIcon("icons\\icons8-send-24.png");
        JButton addCommentButton = new JButton(sendImage);
        addCommentButton.setBackground(new Color(69, 71, 75));
        addCommentButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
        addCommentButton.setBounds(700, 670, 30, 30);
        add(addCommentButton);
        addCommentButton.addActionListener(e -> {
            String comment = null;
            if (!newCommentsPane.getText().equals("Write your comment here")) {
                comment = newCommentsPane.getText();
                String currentComments = newCommentsPane.getText();
                Comment c = new Comment(user.getUserName(), currentComments);
                movie.addComment(c);
                ArrayList<Comment> currentCommentsAfterAdd = movie.getComments();
                commentsPane.setText("");
                updateCommentsPaneColor(currentCommentsAfterAdd, styledDocument);
                newCommentsPane.setText("");
            }
        });
        newCommentsPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addCommentButton.doClick();
                }
            }
        });

        // Display ticket images
        ImageIcon ticket1Image = new ImageIcon("background\\ticket1.png");
        JLabel ticket1Label = new JLabel(ticket1Image);
        ticket1Label.setBounds(780, 140, 300, 119);
        add(ticket1Label);

        ImageIcon ticket2Image = new ImageIcon("background\\ticket2.png");
        JLabel ticket2Label = new JLabel(ticket2Image);
        ticket2Label.setBounds(780, 490, 300, 119);
        add(ticket2Label);

        // Button to book a ticket
        JButton bookTicketButton = new JButton("Book Ticket") {
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

                g2.setColor(Color.DARK_GRAY);
                g2.setFont(new Font("Agency FB", Font.BOLD, 22));

                FontMetrics fm = g.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();

                g2.drawString(text, x - 6, y + 2);

                g2.dispose();
            }
        };
        bookTicketButton.setBorderPainted(false);
        bookTicketButton.setFocusPainted(false);
        bookTicketButton.setContentAreaFilled(false);
        bookTicketButton.setBorder(null);
        bookTicketButton.setBounds(835, 380, 200, 50);
        add(bookTicketButton);
        bookTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SeatSelectionFrame(user, movie);
            }
        });
        List<JButton> buttons = new ArrayList<>();

        // Create a panel to display star rating buttons
        JPanel starPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        starPanel.setBounds(535, 210, 136, 22);

        // Iterate over five buttons to represent the star rating
        for (int i = 1; i <= 5; i++) {
            ImageIcon imageIcon = new ImageIcon("icons\\icons8-star-48.png");
            JButton button = new JButton(imageIcon);
            if(movie.getUserRate(user) == null)
                button.setBackground(Color.lightGray);
            else if (i<=movie.getUserRate(user))
                button.setBackground(new Color(235, 197, 72));
            else
                button.setBackground(Color.lightGray);
            button.setBorderPainted(false);

            // Add an ActionListener to handle button clicks
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Reset the background color for all buttons
                    for (JButton btn : buttons) {
                        btn.setBackground(Color.lightGray);
                    }

                    // Change the background color up to the clicked button to represent the selected rating
                    int clickedButtonIndex = buttons.indexOf(button);
                    for (int j = 0; j <= clickedButtonIndex; j++) {
                        buttons.get(j).setBackground(new Color(235, 197, 72));
                    }

                    // Update the last clicked button, add user rating, and update the displayed average rating
                    lastClickedButton = button;
                    int rating = clickedButtonIndex + 1;
                    movie.addUserRating(user.getUserName(), rating);
                    double averageRating = movie.calculateAverageRating();
                    String[] parts = String.valueOf(averageRating).split("\\.");
                    String decimalPart = parts.length > 1 ? parts[1] : "0";
                    ratingLabel.setText(parts[0] + "." + decimalPart.charAt(0) + " / 5");
                }
            });

            // Add the button to the list and the panel
            buttons.add(button);
            starPanel.add(button);
        }

        // Add the star rating panel to the main panel
        add(starPanel);
    }

    // Helper method to update the comments pane with proper styling
    private void updateCommentsPaneColor(ArrayList<Comment> comments, StyledDocument styledDocument) {
        for (Comment comment : comments) {
            // Style attributes for the user's name
            SimpleAttributeSet userAttributes = new SimpleAttributeSet();
            StyleConstants.setFontSize(userAttributes, 20);
            StyleConstants.setForeground(userAttributes, new Color(0x485D56));
            StyleConstants.setBold(userAttributes, true);

            try {
                // Insert username with styling into the document
                styledDocument.insertString(styledDocument.getLength(), "- " + comment.getUser_username() + ": ", userAttributes);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            // Style attributes for the comment content
            SimpleAttributeSet commentAttributes = new SimpleAttributeSet();
            StyleConstants.setFontSize(commentAttributes, 14);
            StyleConstants.setForeground(commentAttributes, new Color(0x2f2f2f));
            StyleConstants.setFontFamily(userAttributes, "PLAIN");

            try {
                // Insert comment content with styling into the document
                styledDocument.insertString(styledDocument.getLength(), comment.getContent() + "\n", commentAttributes);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
}
