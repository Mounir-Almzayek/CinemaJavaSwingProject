import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * This class represents the Sign Up Page in the application.
 * Users can register for a new account using this page.
 */
public class SignUpFrame extends JFrame {

    public SignUpFrame() {
        super("Sign up Page");
        requestFocusInWindow();
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Left Panel (Logo Panel)
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(73, 94, 87));
        leftPanel.setBounds(0, 0, 325, 600);
        add(leftPanel);

        // Right Panel (Sign Up Form Panel)
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(new Color(245, 247, 248));
        rightPanel.setBounds(325, 0, 675, 600);
        add(rightPanel);

        // Logo
        ImageIcon logoIcon = new ImageIcon("icons\\Logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(60, 180, 200, 200);
        leftPanel.add(logoLabel);

        // Title Label
        JLabel titleLabel = new JLabel("Sign up");
        titleLabel.setFont(new Font("Bahnschrift", Font.BOLD, 35));
        titleLabel.setBounds(590, 90, 150, 50);
        titleLabel.setBackground(Color.DARK_GRAY);
        rightPanel.add(titleLabel);

        // First Name Field
        JTextField firstNameField = new JTextField(12);
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        firstNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        firstNameField.setBounds(510, 170, 135, 35);
        firstNameField.setForeground(Color.lightGray);
        firstNameField.setBackground(new Color(245, 247, 248));
        firstNameField.setText("First name");
        firstNameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (firstNameField.getText().equals("First name")) {
                    firstNameField.setText("");
                    firstNameField.setForeground(Color.darkGray);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (firstNameField.getText().isEmpty()) {
                    firstNameField.setForeground(Color.lightGray);
                    firstNameField.setText("First name");
                }
            }
        });
        rightPanel.add(firstNameField);

        // Last Name Field
        JTextField lastNameField = new JTextField(12);
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        lastNameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        lastNameField.setBounds(660, 170, 150, 35);
        lastNameField.setForeground(Color.lightGray);
        lastNameField.setBackground(new Color(245, 247, 248));
        lastNameField.setText("Last name");
        lastNameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (lastNameField.getText().equals("Last name")) {
                    lastNameField.setText("");
                    lastNameField.setForeground(Color.darkGray);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (lastNameField.getText().isEmpty()) {
                    lastNameField.setForeground(Color.lightGray);
                    lastNameField.setText("Last name");
                }
            }
        });
        rightPanel.add(lastNameField);

        // Username Field
        JTextField usernameField = new JTextField(25);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        usernameField.setBounds(510, 230, 300, 35);
        usernameField.setForeground(Color.lightGray);
        usernameField.setBackground(new Color(245, 247, 248));
        usernameField.setText("Username");
        usernameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.darkGray);
                }
            }

            public void focusLost(FocusEvent evt) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setForeground(Color.lightGray);
                    usernameField.setText("Username");
                }
            }
        });
        rightPanel.add(usernameField);

        // Password Field
        JPasswordField passwordField = new JPasswordField(25);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        passwordField.setBounds(510, 290, 300, 35);
        passwordField.setForeground(Color.lightGray);
        passwordField.setBackground(new Color(245, 247, 248));
        passwordField.setText("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.darkGray);
                    passwordField.setEchoChar('●');
                }
            }

            public void focusLost(FocusEvent evt) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setForeground(Color.lightGray);
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char) 0);
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String email = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String gender = "--";//(String) genderComboBox.getSelectedItem();
                    User user = new User(firstName, lastName, email, password, gender);
                    if (User.addData(user)) {
                        dispose();
                        new SignInFrame();
                    } else {
                        JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        rightPanel.add(passwordField);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign up") {
            @Override
            protected void paintComponent(Graphics g) {
                // Create Graphics2D for drawing
                Graphics2D g2 = (Graphics2D) g.create();

                // Set the arc diameter to make the button elliptical
                int arcDiameter = 35;

                // Create RoundRectangle2D for drawing the shape with curved edges
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);

                // Set the button color based on the mouse pointer state
                if (getModel().isArmed()) {
                    g2.setColor(new Color(69, 71, 75)); // Color when pressed
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(235, 197, 72)); // Color when hovered
                } else {
                    g2.setColor(new Color(69, 71, 75)); // Default color
                }

                // Fill the shape with the specified color
                g2.fill(roundRect);

                // Set the text color and font
                g2.setColor(Color.white);
                g2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));

                // Retrieve text dimensions
                FontMetrics fm = g.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                // Calculate the correct position to center the text
                int x = (getWidth() - textWidth) / 2;
                int y = (getHeight() - textHeight) / 2 + fm.getAscent();

                // Draw the text inside the button
                g2.drawString(text, x - 10, y + 2);

                // Dispose of the temporary memory
                g2.dispose();
            }
        };
        signUpButton.setBorderPainted(false); // Set this property to hide the button borders
        signUpButton.setFocusPainted(false); // Set this property to prevent button highlighting on focus
        signUpButton.setContentAreaFilled(false); // Set this property to prevent coloring the button's interior on click
        signUpButton.setBorder(null);
        signUpButton.setBounds(535, 365, 250, 35);
        rightPanel.add(signUpButton);

        // "Already Have an Account" Label
        JLabel haveAccountLabel = new JLabel("Already Have an Account");
        haveAccountLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        haveAccountLabel.setBounds(580, 410, 300, 50);
        haveAccountLabel.setForeground(new Color(0x2f2f2f));
        rightPanel.add(haveAccountLabel);

        // Sign In Button
        JButton signInButton = new JButton("Sign in");
        signInButton.setFont(new Font("Bahnschrift", Font.BOLD, 22));
        signInButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        signInButton.setBackground(new Color(245, 247, 248));
        signInButton.setForeground(new Color(73, 94, 87));
        signInButton.setBounds(608, 450, 100, 30);
        signInButton.setBorderPainted(false); // Set this property to hide the button borders
        signInButton.setFocusPainted(false); // Set this property to prevent button highlighting on focus
        signInButton.setContentAreaFilled(false); // Set this property to prevent coloring the button's interior on click
        rightPanel.add(signInButton);

        // Sign Up Button Action Listener
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(firstNameField.getForeground().equals(Color.lightGray) || lastNameField.getForeground().equals(Color.lightGray) || usernameField.getForeground().equals(Color.lightGray) || passwordField.getForeground().equals(Color.lightGray))){
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String email = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String gender = "--";//(String) genderComboBox.getSelectedItem();
                    User user = new User(firstName, lastName, email, password, gender);
                    if (User.addData(user)) {
                        dispose();
                        new SignInFrame();
                    } else {
                        JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "The fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        // Sign In Button Action Listener
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignInFrame();
            }
        });

        // Window Listener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // Set the value to null to remove focus from any element
                SwingUtilities.invokeLater(() -> getRootPane().requestFocusInWindow());
            }
        });

        setVisible(true);
    }
}
