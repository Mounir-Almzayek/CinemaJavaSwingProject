import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the Startup Frame in the application.
 * It displays a logo and a loading icon before redirecting to the SignInFrame.
 */
public class StartupFrame extends JFrame {

    public StartupFrame() {
        super("Login Page");
        requestFocusInWindow();
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(73, 94, 87));
        panel.setBounds(0, 0, 500, 300);
        add(panel);

        // Logo
        ImageIcon logoIcon = new ImageIcon("icons\\Logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(145, 20, 200, 200);
        panel.add(logoLabel);

        // Loading Icon
        ImageIcon loudIcon = new ImageIcon("icons\\ajax-loader.gif");
        JLabel loudLabel = new JLabel(loudIcon);
        loudLabel.setBounds(225, 200, 40, 40);
        panel.add(loudLabel);

        // Timer to delay and open the SignInFrame
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                openNewFrame();
            }
        });
        timer.setRepeats(false);
        setVisible(true);
        timer.start();
    }

    private void openNewFrame() {
        SwingUtilities.invokeLater(() -> {
            SignInFrame newFrame = new SignInFrame();
            newFrame.setVisible(true);
        });
    }
}
