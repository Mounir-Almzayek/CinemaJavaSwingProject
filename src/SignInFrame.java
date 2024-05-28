import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
/**
 * The SignInFrame class represents the main frame for the sign-in page.
 */

public class SignInFrame extends JFrame {
    public SignInFrame() {
        super("Sign in Page");
        requestFocusInWindow();
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(73, 94, 87));
        leftPanel.setBounds(0,0,325,600);
        add(leftPanel);

        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(new Color(245, 247, 248));
        rightPanel.setBounds(325,0,675,600);
        add(rightPanel);

        ImageIcon logoIcon = new ImageIcon("icons\\Logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(60, 180, 200, 200);
        leftPanel.add(logoLabel);

        JLabel titleLabel = new JLabel("Sign in");
        titleLabel.setFont(new Font("Bahnschrift", Font.BOLD, 35));
        titleLabel.setBounds(595, 90, 150, 50);
        titleLabel.setForeground(Color.DARK_GRAY);
        rightPanel.add(titleLabel);

        JTextField usernameField = new JTextField(25);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        usernameField.setBounds(510, 190, 300, 35);
        usernameField.setForeground(Color.lightGray);
        usernameField.setBackground(new Color(245, 247, 248));
        usernameField.setText("Username");
        usernameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (usernameField.getForeground().equals(Color.lightGray)) {
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

        JPasswordField passwordField = new JPasswordField(25);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        passwordField.setBounds(510, 260, 272, 35);
        passwordField.setForeground(Color.lightGray);
        passwordField.setBackground(new Color(245, 247, 248));
        passwordField.setText("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (passwordField.getForeground().equals(Color.lightGray)) {
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

        rightPanel.add(passwordField);

        JToggleButton showPasswordToggleButton = new JToggleButton();
        showPasswordToggleButton.setBackground(new Color(245, 247, 248));
        showPasswordToggleButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        ImageIcon showIcon = new ImageIcon("icons\\icons8-eye-24.png"); // replace with your icon file path
        ImageIcon hideIcon = new ImageIcon("icons\\icons8-hide-24.png"); // replace with your icon file path
        showPasswordToggleButton.setIcon(hideIcon);
        showPasswordToggleButton.setSelectedIcon(showIcon);
        showPasswordToggleButton.setBounds(781, 260, 30, 35);
        showPasswordToggleButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('●');
                }
            }
        });
        showPasswordToggleButton.setFocusPainted(false);
        rightPanel.add(showPasswordToggleButton);

        JButton singInButton = new JButton("Sign in"){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 35;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
                if (getModel().isArmed()) {
                    g2.setColor(new Color(69, 71, 75));
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(72, 93, 86));
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
                g2.drawString(text, x-8, y+2);
                g2.dispose();
            }
        };
        singInButton.setBorderPainted(false);
        singInButton.setFocusPainted(false);
        singInButton.setContentAreaFilled(false);
        singInButton.setBorder(null);
        singInButton.setForeground(Color.white);
        singInButton.setBounds(535 , 360, 250, 35);
        rightPanel.add(singInButton);

        JLabel haveAccountLabel = new JLabel("Don't Have an Account");
        haveAccountLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        haveAccountLabel.setBounds(588, 410, 300, 50);
        haveAccountLabel.setForeground(new Color(0x2f2f2f));
        rightPanel.add(haveAccountLabel);

        JButton signUpButton = new JButton("Sign up");
        signUpButton.setFont(new Font("Bahnschrift", Font.BOLD, 22));
        signUpButton.setBorder(BorderFactory.createEmptyBorder());
        signUpButton.setBackground(new Color(245, 247, 248));
        signUpButton.setForeground(new Color(73, 94, 87));
        signUpButton.setBounds(610, 450, 100, 30);
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);
        signUpButton.setContentAreaFilled(false);
        rightPanel.add(signUpButton);

        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChar = passwordField.getPassword();
                String password = String.copyValueOf(passwordChar);
                User user = new User();
                user.setUserName(username);
                user.setPassword(password);

                if (user.login() == null) {
                    JOptionPane.showMessageDialog(null, "wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                    new SideBarFrame(user.login());
                }
            }
        });

        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChar = passwordField.getPassword();
                String password = String.copyValueOf(passwordChar);
                User user = new User();
                user.setUserName(username);
                user.setPassword(password);

                if (user.login() == null) {
                    JOptionPane.showMessageDialog(null, "wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                    new SideBarFrame(user.login());
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignUpFrame();
            }
        });

        singInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(usernameField.getForeground().equals(Color.lightGray) || passwordField.getForeground().equals(Color.lightGray))) {
                    String username = usernameField.getText();
                    char[] passwordChar = passwordField.getPassword();
                    String password = String.copyValueOf(passwordChar);
                    User user = new User();
                    user.setUserName(username);
                    user.setPassword(password);
                    if (user.login() == null) {
                        JOptionPane.showMessageDialog(null, "wrong username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        dispose();
                        new SideBarFrame(user.login());
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "The fields cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                SwingUtilities.invokeLater(() -> getRootPane().requestFocusInWindow());
            }
        });
        setVisible(true);
    }
}
