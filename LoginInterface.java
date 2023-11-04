import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LoginInterface() {
        createUI();
    }


    private void createUI() {
        // Set up the frame
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create components
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);

        // Set up the layout
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(statusLabel);
        setVisible(true);



        // Add action listener to the button
        loginButton.addActionListener(e -> {
            if ("admin".equals(usernameField.getText()) && "password".equals(new String(passwordField.getPassword()))) {
                statusLabel.setText("Login successful!");
                LibraryHomeScreen home = new LibraryHomeScreen();
                home.setVisible(true);
                setVisible(false);

            } else {
                statusLabel.setText("Login failed!");
            }
        });
    }


}