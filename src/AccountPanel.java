
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

/**
 * This class represents the Account Panel in the application.
 */
class AccountPanel extends JPanel {
    private User user;
    private boolean isEditing = false;

    private JTextField userFirstNameField;
    private JTextField userLastNameField;
    private JTextField userUserNameField;
    private JPasswordField userPasswordField;
    private JComboBox<String> userGenderComboBox;

    public AccountPanel(User user) {
        this.user = user;
        setLayout(null);

        // Background Image
        ImageIcon homePage = new ImageIcon("background\\personal data.png");
        JLabel topHomeImageLabel = new JLabel(homePage);
        topHomeImageLabel.setBounds(220, 10, 1000, 750);
        add(topHomeImageLabel);

        // Your Information Label
        JLabel yourInformationLabel = new JLabel("<html><font color='#EBC548FF'>|</font> " + " Your Information");
        yourInformationLabel.setFont(new Font("Arial", Font.BOLD, 35));
        yourInformationLabel.setForeground(new Color(69, 71, 75));
        yourInformationLabel.setBounds(50, 75, 400, 50);
        add(yourInformationLabel);

        // Labels and Text Fields for User Information

        JLabel userFirstLabel = new JLabel("First Name:");
        userFirstLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userFirstLabel.setBounds(75, 170, 150, 20);
        userFirstLabel.setBackground(Color.DARK_GRAY);
        add(userFirstLabel);

        userFirstNameField = createTextField(user.getFirstName(), 200, 170);

        JLabel userLastNameLabel = new JLabel("Last Name:");
        userLastNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userLastNameLabel.setBounds(75, 230, 150, 20);
        userLastNameLabel.setBackground(Color.DARK_GRAY);
        add(userLastNameLabel);

        userLastNameField = createTextField(user.getLastName(), 200, 230);

        JLabel userUserNameLabel = new JLabel("User Name:");
        userUserNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userUserNameLabel.setBounds(75, 290, 150, 20);
        userUserNameLabel.setBackground(Color.DARK_GRAY);
        add(userUserNameLabel);

        userUserNameField = createTextField(user.getUserName(), 200, 290);

        JLabel userPasswordLabel = new JLabel("Password:");
        userPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userPasswordLabel.setBounds(75, 350, 150, 20);
        userPasswordLabel.setBackground(Color.DARK_GRAY);
        add(userPasswordLabel);

        userPasswordField =  createPasswordField(user.getPassword(),200,350);

        JLabel userGenderLabel = new JLabel("Gender:");
        userGenderLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userGenderLabel.setBounds(75, 410, 150, 20);
        userGenderLabel.setBackground(Color.DARK_GRAY);
        add(userGenderLabel);

        userGenderComboBox = createGenderComboBox(user.getGender(), 200, 405);

        userGenderComboBox.setEnabled(false);
        userGenderComboBox.setBackground(Color.white);




        // Edit/Save Button
        JToggleButton editSaveButton = new JToggleButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                int arcDiameter = 20;
                RoundRectangle2D roundRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcDiameter, arcDiameter);
                g2.fill(roundRect);
                g2.dispose();
            }
        };
        configureButton(editSaveButton, "Edit", "icons\\icons8-edit-24.png", 80, 480, 250, 50);
        editSaveButton.addActionListener(e -> toggleEdit());
        add(editSaveButton);

        // Logout Button
        JButton logoutButton = new JButton() {
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
        configureButton(logoutButton, "Logout", "icons\\icons8-logout-32.png", 80, 550, 250, 50);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(AccountPanel.this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(AccountPanel.this, "Logged out successfully!");
                    SwingUtilities.getWindowAncestor(AccountPanel.this).dispose();
                    new SignInFrame();
                }
            }
        });
        add(logoutButton);

        add(new JLabel());
        add(new JLabel());
    }

    private JTextField createTextField(String initialValue, int x, int y) {
        JTextField textField = new JTextField(initialValue);
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        textField.setBounds(x, y, 150, 20);
        add(textField);
        return textField;
    }

    private JPasswordField createPasswordField(String initialValue, int x, int y) {
        JPasswordField passwordField = new JPasswordField(initialValue);
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        passwordField.setBounds(x, y, 150, 20);
        passwordField.setEchoChar('●');
        add(passwordField);
        return passwordField;
    }

    private JComboBox<String> createGenderComboBox(String initialValue, int x, int y) {
        String[] genderOptions = {"Male", "Female"};
        JComboBox<String> comboBox = new JComboBox<>(genderOptions);
        comboBox.setSelectedItem(initialValue);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        comboBox.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        add(comboBox);
        comboBox.setBounds(x, y, 150, 30);
        return comboBox;
    }

    private void toggleEdit() {
        if (!isEditing) {
            // Enable editing
            isEditing = true;
            userFirstNameField.setEditable(true);
            userLastNameField.setEditable(true);
            userUserNameField.setEditable(true);
            userPasswordField.setEditable(true);
            userGenderComboBox.setEnabled(true);
        } else {
            // Save changes and disable editing
            isEditing = false;
            String username = user.getUserName();
            user.setFirstName(userFirstNameField.getText());
            user.setLastName(userLastNameField.getText());
            user.setUserName(userUserNameField.getText());
            user.setPassword(userPasswordField.getText());
            user.setGender((String) userGenderComboBox.getSelectedItem());

            if(user.editData(username)){
                JOptionPane.showMessageDialog(this, "Changes saved successfully!");

                // Update the text fields with the new values
                userFirstNameField.setText(user.getFirstName());
                userLastNameField.setText(user.getLastName());
                userUserNameField.setText(user.getUserName());
                userPasswordField.setText(user.getPassword());
                userGenderComboBox.setSelectedItem(user.getGender());

                // Disable editing
                userFirstNameField.setEditable(false);
                userLastNameField.setEditable(false);
                userUserNameField.setEditable(false);
                userPasswordField.setEditable(false);
                userGenderComboBox.setEnabled(false);

            }else {
                JOptionPane.showMessageDialog(this, "Username already exist");
            }
        }
    }

    private void configureButton(AbstractButton button, String labelText, String iconPath, int x, int y, int width, int height) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        JLabel label = new JLabel("            " + labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(Color.white);
        ImageIcon imageIcon = new ImageIcon(iconPath);
        button.setLayout(new GridLayout(1, 3));
        button.add(label);
        button.add(new JLabel(imageIcon));
        button.setBounds(x, y, width, height);
        button.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
        button.setBackground(new Color(0x2f2f2f));
    }
}