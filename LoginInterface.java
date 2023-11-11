import javax.swing.*;
import java.awt.*;

public class LoginInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LoginInterface() {
        createUI();
    }

    private void createUI() {
        setTitle("Library Management System - Login");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton);

        statusLabel = new JLabel("Enter your credentials", SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        setVisible(true);

        loginButton.addActionListener(e -> {
            if (authenticate(usernameField.getText(), new String(passwordField.getPassword()))) {
                statusLabel.setText("Login successful!");
                statusLabel.setForeground(Color.GREEN);
                LibraryHomeScreen home = new LibraryHomeScreen();
                home.setVisible(true);
                setVisible(false);
            } else {
                statusLabel.setText("Login failed! Please try again.");
                statusLabel.setForeground(Color.RED);
            }
        });
    }

    private boolean authenticate(String username, String password) {
        return LibraryDatabase.authenticate(username, password);
    }


}
