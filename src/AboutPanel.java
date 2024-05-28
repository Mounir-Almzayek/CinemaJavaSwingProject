import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

/**
 * This class represents the About Panel in the application.
 */
public class AboutPanel extends JPanel {

    AboutPanel(User user) {
        setLayout(null);

        // Title Labels
        JLabel title1 = new JLabel("Your way to choose ");
        title1.setFont(new Font("Arial", Font.BOLD, 65));
        title1.setBounds(80, 160, 900, 70);
        add(title1);

        JLabel title2 = new JLabel("The best movies ");
        title2.setFont(new Font("Arial", Font.BOLD, 65));
        title2.setBounds(80, 240, 900, 60);
        add(title2);

        JLabel title3 = new JLabel("We provide many options to choose your");
        title3.setFont(new Font("Calibri Light", Font.PLAIN, 24));
        title3.setBounds(90, 315, 900, 60);
        add(title3);

        JLabel title4 = new JLabel("suitable movie in the cinema hall ");
        title4.setFont(new Font("Calibri Light", Font.PLAIN, 24));
        title4.setBounds(90, 345, 900, 60);
        add(title4);

        // Coming Soon Button
        JButton comingSoonButton = new JButton() {
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
                    g2.setColor(new Color(0x2f2f2f));
                }
                g2.fill(roundRect);
                g2.dispose();
            }
        };
        JLabel comingSoonLabel = new JLabel("    Coming soon");
        comingSoonLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        comingSoonLabel.setForeground(Color.white);
        ImageIcon comingSoonImage = new ImageIcon("icons\\icons8-coming-soon-32.png");
        comingSoonButton.setLayout(new GridLayout(1, 3));
        comingSoonButton.add(comingSoonLabel);
        comingSoonButton.add(new JLabel(comingSoonImage));
        comingSoonButton.setBounds(110, 480, 300, 50);
        comingSoonButton.setBorderPainted(false);
        comingSoonButton.setFocusPainted(false);
        comingSoonButton.setContentAreaFilled(false);
        comingSoonButton.setBackground(new Color(0x2f2f2f));
        add(comingSoonButton);

        // Most Popular Button
        JButton mostPopularButton = new JButton() {
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
                    g2.setColor(new Color(0x2f2f2f));
                }
                g2.fill(roundRect);
                g2.dispose();
            }
        };
        JLabel mostPopularLabel = new JLabel("    Most popular");
        mostPopularLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        mostPopularLabel.setForeground(Color.white);
        ImageIcon mostPopularImage = new ImageIcon("icons\\icons8-popular-32.png");
        mostPopularButton.setLayout(new GridLayout(1, 3));
        mostPopularButton.add(mostPopularLabel);
        mostPopularButton.add(new JLabel(mostPopularImage));
        mostPopularButton.setBounds(110, 550, 300, 50);
        mostPopularButton.setBorderPainted(false);
        mostPopularButton.setFocusPainted(false);
        mostPopularButton.setContentAreaFilled(false);
        mostPopularButton.setBackground(new Color(0x2f2f2f));
        add(mostPopularButton);

        // Home Image
        ImageIcon homePage = new ImageIcon("background\\home page.png");
        JLabel topHomeImageLabel = new JLabel(homePage);
        topHomeImageLabel.setBounds(125, 25, 1000, 700);
        add(topHomeImageLabel);

        // Action Listeners
        comingSoonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) HomePanel.cardPanel.getLayout();
                cardLayout.show(HomePanel.cardPanel, "ComingSoon");
            }
        });

        mostPopularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) HomePanel.cardPanel.getLayout();
                cardLayout.show(HomePanel.cardPanel, "MostPopular");
            }
        });
    }
}
