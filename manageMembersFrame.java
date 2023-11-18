import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * Class representing the frame for managing members in the library management system.
 */
public class manageMembersFrame extends JFrame {
    private JTextField userIdField, usernameField;
    private JPasswordField passwordField;
    private JTextArea memberDetailsArea;
    private JButton addButton, updateButton, removeButton;
    private JTable memberTable;
    private DefaultTableModel tableModel;

    /**
     * Constructor for ManageMembersFrame.
     * Sets up the UI components for managing members.
     */
    public manageMembersFrame() {
        // Frame setup
        setTitle("Manage Members");
        setSize(1000, 1100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Adding a title label
        JLabel title = new JLabel("Members Management", SwingConstants.CENTER);
        title.setFont(new Font("Aharoni", Font.BOLD, 58));
        title.setBounds(230, 5, 700, 80);
        add(title);

        // Setting up the middle screen panel
        JPanel midScreen = new JPanel();
        midScreen.setLayout(new GridLayout(0, 2, 10, 10));
        midScreen.setBounds(50, 155, 900, 600);
        midScreen.setBackground(Color.PINK);
        add(midScreen);

        // Adding form fields to the panel
        userIdField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        midScreen.add(new JLabel("User ID:"));
        midScreen.add(userIdField);
        midScreen.add(new JLabel("Username:"));
        midScreen.add(usernameField);
        midScreen.add(new JLabel("Password:"));
        midScreen.add(passwordField);

        // Adding buttons for member operations
        updateButton = new JButton("Update Member");
        addButton = new JButton("Register");
        removeButton = new JButton("Remove Member");
        midScreen.add(updateButton);
        midScreen.add(addButton);
        midScreen.add(removeButton);

        // Action listeners for the buttons
        updateButton.addActionListener(e -> updateMember());
        addButton.addActionListener(e -> addMember());
        removeButton.addActionListener(e -> removeMember());

        // Setting up and adding the member table
        Vector<String> columnNames = getColumnNames();
        tableModel = new DefaultTableModel(LibraryDatabase.getMemberDataVector(), columnNames);
        memberTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setBounds(50, 400, 900, 300);
        midScreen.add(scrollPane);

        // Adding home button
        ImageIcon homeIcon = new ImageIcon("home.png");
        JButton home = new JButton(homeIcon);
        home.addActionListener(e -> {
            new LibraryHomeScreen();
            dispose();
        });
        home.setBounds(930, 700, 64, 64);
        add(home);

        // Make the frame visible
        setVisible(true);
    }

    /**
     * Method for adding a member.
     */
    private void addMember() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean success = LibraryDatabase.addUser(new User(username, password));
        if (success) {
            JOptionPane.showMessageDialog(this, "Member added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add member.");
        }
        refreshMemberList();    }

    /**
     * Method for updating member information.
     */
    private void updateMember() {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            if (LibraryDatabase.isUseridValid(userId)) {
                // Prompt the user for a new username
                String newUsername = JOptionPane.showInputDialog(this, "Enter new username:");
                if (newUsername != null) {
                    // Prompt the user for a new password
                    JPasswordField passwordField = new JPasswordField();
                    Object[] passwordPanel = {"Enter new password:", passwordField};
                    int passwordResult = JOptionPane.showConfirmDialog(this, passwordPanel, "Password", JOptionPane.OK_CANCEL_OPTION);

                    if (passwordResult == JOptionPane.OK_OPTION) {
                        // User clicked OK, get the entered password
                        char[] newPasswordChars = passwordField.getPassword();
                        String newPassword = new String(newPasswordChars);

                        // Update username and password separately
                        boolean usernameUpdated = LibraryDatabase.updateUsername(userId, newUsername);
                        boolean passwordUpdated = LibraryDatabase.updatePassword(userId, newPassword);
                        refreshMemberList();
                        if (usernameUpdated && passwordUpdated) {
                            JOptionPane.showMessageDialog(this, "Member updated successfully.");
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to update member.");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid User ID.");
            }
        } catch (NumberFormatException ex) {
            memberDetailsArea.setText("Invalid user ID.");
        }
    }

    /**
     * Method for removing a member.
     */
    private void removeMember() {
        try {
            String username = usernameField.getText();
            boolean success = LibraryDatabase.removeUser(username);
            if (success) {
                JOptionPane.showMessageDialog(this, "Member removed successfully.");
                refreshMemberList();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove member.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid User ID");
        }
    }
    /**
     * Refreshes the member list in the table.
     */
    private void refreshMemberList() {
        Vector<Vector<Object>> dataVector = LibraryDatabase.getMemberDataVector();
        Vector<String> columnName = new Vector<>();

        columnName.add("User Id");
        columnName.add("Username");

        tableModel.setDataVector(dataVector, columnName );
    }

    /**
     * Returns a vector of column names for the member table.
     */
    private Vector<String> getColumnNames() {
        Vector<String> columnNames = new Vector<>();
        columnNames.add("User ID");
        columnNames.add("Username");
        return columnNames;
    }


}
