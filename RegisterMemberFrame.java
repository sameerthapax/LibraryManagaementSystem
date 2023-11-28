import javax.swing.*;
import java.awt.*;

/**
 * Class representing the frame for registering a new member in the library management system.
 */
public class RegisterMemberFrame extends JFrame {
    private JTextField usernameField, phoneNumberField;
    private JPasswordField passwordField;
    private JButton registerButton, loginButton;

    /**
     * Constructor for RegisterMemberFrame.
     * Sets up the UI components for registering a new member.
     */
    public RegisterMemberFrame() {
         // Frame setup
         setTitle("Member Registeration");
         setSize(1000, 1100);
         setLocationRelativeTo(null);
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         setLayout(null);
 
         // Adding a title label
         JLabel title = new JLabel("Member Registeration", SwingConstants.CENTER);
         title.setFont(new Font("Aharoni", Font.BOLD, 58));
         title.setBounds(140, 5, 700, 80);
         add(title);
 
         // Setting up the middle screen panel
         JPanel midScreen = new JPanel();
         midScreen.setLayout(new BorderLayout());
         JPanel splitScreen1 = new JPanel();
 
         midScreen.setBounds(50, 120, 900, 600);
         midScreen.setBackground(Color.PINK);
         add(midScreen);
         midScreen.add(splitScreen1, BorderLayout.CENTER);
 
 
         splitScreen1.setLayout(new GridLayout(0,2, 10, 10));
         splitScreen1.setOpaque(false);
 
         Font formFieldFont = new Font("Gurmukhi MN", Font.PLAIN, 38);
         // Adding form fields to the panel
         JPanel fieldPanel1 = new JPanel(null);
         fieldPanel1.setOpaque(false);
         JPanel fieldPanel2 = new JPanel(null);
         fieldPanel2.setOpaque(false);
         JPanel fieldPanel3 = new JPanel(null);
         fieldPanel3.setOpaque(false);
         JPanel fieldPanel4 = new JPanel(null);
         fieldPanel4.setOpaque(false);
         
         JLabel username = new JLabel("Username:");
         username.setFont(formFieldFont);
         username.setBounds(150,10,200,100);
         fieldPanel1.add(username);
         usernameField = new JTextField();
 
         JLabel password = new JLabel("Password:");
         password.setFont(formFieldFont);
         password.setBounds(150,10,200,100);
         fieldPanel2.add(password);
         passwordField = new JPasswordField();
 
 
         JLabel phoneNumber = new JLabel("Phone No:");
         phoneNumber.setFont(formFieldFont);
         phoneNumber.setBounds(150,10,200,100);
         fieldPanel3.add(phoneNumber);
         phoneNumberField = new JTextField();
 
         JLabel role = new JLabel("Role:");
         role.setFont(formFieldFont);
         role.setBounds(150,10,200,100);
         fieldPanel4.add(role);
        
 
         splitScreen1.add(fieldPanel1);
         splitScreen1.add(usernameField);
         splitScreen1.add(fieldPanel2);
         splitScreen1.add(passwordField);
         splitScreen1.add(fieldPanel3);
         splitScreen1.add(phoneNumberField);
         splitScreen1.add(fieldPanel4);
 
        

        // Adding register button
        registerButton = new JButton("Register");
        registerButton.setBounds(150, 140, 100, 30);

        loginButton = new JButton("Log In");
        loginButton.setBounds(400, 260, 200, 50);


        // Add components to the frame
        add(fieldPanel1);
        add(fieldPanel2);
        add(fieldPanel3);
        add(usernameField);
        add(passwordField);
        add(phoneNumberField);
        add(registerButton);
        add(loginButton);

        // Add action listener for register button
        registerButton.addActionListener(e -> registerMember());
        loginButton.addActionListener(e -> {
            dispose(); // Close the registration frame
            openLoginInterface(); // Open the login interface
        });
    }

    /**
     * Method for registering a new member.
     */
    private void registerMember() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String phoneNumber = phoneNumberField.getText();
        String role = "Not Admin";
        User newUser = new User(username, password, phoneNumber,role);
        newUser.setPhoneNumber(phoneNumber);

        boolean success = LibraryDatabase.addUser(newUser);
        if (success) {
            JOptionPane.showMessageDialog(this, "Member registered successfully.");
            dispose(); // Close the registration frame
        } else {
            JOptionPane.showMessageDialog(this, "Failed to register member.");
        }
    }
    private void openLoginInterface() {
        new LoginInterface();
    }

}
