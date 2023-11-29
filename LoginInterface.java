import javax.swing.*;
import java.awt.*;

/**
 * Class representing the login interface for the library management system.
 */
public class LoginInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    /**
     * Constructor for LoginInterface.
     * Initializes the user interface.
     */
    public LoginInterface() {
        createUI();
    }

    /**
     * Creates and arranges UI components.
     */
    private void createUI() {
        // Frame setup
        setTitle("Library Management System - Login");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input panel for username and password
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        // Button panel with login button
        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        JButton becomeMemberButton = new JButton("Become a Member");
        buttonPanel.add(becomeMemberButton);
        buttonPanel.add(loginButton);


        // Status label to display login messages
        statusLabel = new JLabel("Enter your credentials (Case Sensitive)", SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE);

        // Add panels and label to the frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        // Set the frame visibility
        setVisible(true);

        // Add action listener for login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (LibraryDatabase.authenticate(username,password)) {
                // Set the current user
                User loggedInUser = LibraryDatabase.getUser(username);
                CurrentUser.setCurrentUser(loggedInUser);
                statusLabel.setText("Login successful!");
                statusLabel.setForeground(Color.GREEN);
                LibraryHomeScreen home = new LibraryHomeScreen();
                home.setVisible(true);
                dispose();
            } else {
                statusLabel.setText("Login failed! Please try again.");
                statusLabel.setForeground(Color.RED);
            }
        });
        becomeMemberButton.addActionListener(e -> openRegisterMemberFrame());
    
    }

    private void openRegisterMemberFrame() {
        RegisterMemberFrame registerFrame = new RegisterMemberFrame();
        registerFrame.setVisible(true);
        dispose();
    }
    
}
