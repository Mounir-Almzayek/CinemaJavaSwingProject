import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The SideBarFrame class represents the main frame of the Movie App with a sidebar for navigation.
 */
public class SideBarFrame extends JFrame {
    static public JPanel cardPanel;
    private JButton homeButton;
    private JButton moviesButton;
    private JButton ticketsButton;
    private JButton accountButton;
    static public JButton currentButton;
    static HomePanel homePanel;
    static MoviesPanel moviesPanel;
    static TicketsPanel ticketsPanel;
    static AccountPanel accountPanel;

    public SideBarFrame(User user) {
        super("Movie App");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        JPanel sidePanel = createSidePanel();
        sidePanel.setBounds(0, 0, 250, 800);
        add(sidePanel);
        
        cardPanel = new JPanel(new CardLayout());
        cardPanel.setBounds(250, 0, 1150, 800);

        homePanel = new HomePanel(user);
        cardPanel.add(homePanel, "home");

        moviesPanel = new MoviesPanel(user);
        cardPanel.add(moviesPanel, "movies");

        ticketsPanel = new TicketsPanel(user);
        cardPanel.add(ticketsPanel, "tickets");

        accountPanel = new AccountPanel(user);
        cardPanel.add(accountPanel, "account");
        add(cardPanel);

        setVisible(true);
        handleButtonClick(homeButton, "home");
    }

    /**
     * Creates the sidebar panel with buttons for navigation.
     *
     * @return The created sidebar panel.
     */
    private JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(73, 94, 87));

        // Label for user welcome message
        JLabel primaryAddress = new JLabel(" WELCOME!");
        primaryAddress.setFont(new Font("Arial", Font.PLAIN, 25));
        primaryAddress.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(245, 247, 248)));
        primaryAddress.setBounds(25, 60, 190, 48);
        primaryAddress.setForeground(new Color(245, 247, 248));
        sidePanel.add(primaryAddress);

        // Buttons for different panels
        homeButton = createIconButton("icons\\icons8-homepage-24.png", "HOME");
        homeButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.lightGray));
        homeButton.setBounds(0, 150, 250, 70);
        sidePanel.add(homeButton);

        moviesButton = createIconButton("icons\\icons8-movie-24.png", "MOVIES");
        moviesButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.lightGray));
        moviesButton.setBounds(0, 220, 250, 70);
        sidePanel.add(moviesButton);

        ticketsButton = createIconButton("icons\\icons8-tickets-24 (1).png", "TICKETS");
        ticketsButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.lightGray));
        ticketsButton.setBounds(0, 290, 250, 70);
        sidePanel.add(ticketsButton);

        accountButton = createIconButton("icons\\icons8-user-24.png", "ACCOUNT");
        accountButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.lightGray));
        accountButton.setBounds(0, 360, 250, 70);
        sidePanel.add(accountButton);

        return sidePanel;
    }

    private JButton createIconButton(String iconName, String buttonText) {
        ImageIcon icon = new ImageIcon(iconName);
        JButton button = new JButton();
        button.setLayout(new GridLayout(1, 3));

        JLabel label = new JLabel(buttonText, JLabel.CENTER);
        label.setFont(new Font("Hacen Tunisia", Font.BOLD, 15));
        label.setForeground(Color.WHITE);

        button.add(new JLabel(icon));
        button.add(label);
        button.add(new JLabel());

        button.setBackground(new Color(73, 94, 87));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleButtonClick(button, buttonText.toLowerCase());
            }
        });

        return button;
    }

    static public void handleButtonClick(JButton clickedButton, String panelName) {
        if (currentButton != null) {
            currentButton.setBackground(new Color(73, 94, 87));
        }
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, panelName);
        clickedButton.setBackground(new Color(107, 129, 120));
        currentButton = clickedButton;

        // Special handling for the home panel
        if (panelName.equals("home")) {
            CardLayout homeCardLayout = (CardLayout) HomePanel.cardPanel.getLayout();
            homeCardLayout.show(HomePanel.cardPanel, "about");
            clickedButton.setBackground(new Color(107, 129, 120, 255));
        }
    }
}
