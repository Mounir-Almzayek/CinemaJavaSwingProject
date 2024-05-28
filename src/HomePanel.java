import javax.swing.*;
import java.awt.*;

/**
 * The HomePanel class represents the main panel in the application.
 * It extends JPanel and contains a CardLayout to switch between different panels.
 */
public class HomePanel extends JPanel {
    // Static variable to hold the cardPanel for switching between panels
    static public JPanel cardPanel;

    public HomePanel(User user) {
        setLayout(null);

        // Create a cardPanel with CardLayout to switch between different panels
        cardPanel = new JPanel(new CardLayout());
        cardPanel.setBounds(0, 0, 1150, 800);

        // Add different panels to the cardPanel
        cardPanel.add(new AboutPanel(user), "about");
        cardPanel.add(new ComingSoon(user), "ComingSoon");
        cardPanel.add(new MostPopular(user), "MostPopular");

        // Add the cardPanel to the HomePanel
        add(cardPanel);
    }
}
