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
    private JButton addButton,removeButton;
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
        JLabel userId = new JLabel("UserId:");
        userId.setFont(formFieldFont);
        userId.setBounds(150,10,200,100);
        fieldPanel1.add(userId);
        userIdField = new JTextField();
        JLabel username = new JLabel("Username:");
        username.setFont(formFieldFont);
        username.setBounds(150,10,200,100);
        fieldPanel2.add(username);
        usernameField = new JTextField();
        JLabel password = new JLabel("Password:");
        password.setFont(formFieldFont);
        password.setBounds(150,10,200,100);
        fieldPanel3.add(password);
        passwordField = new JPasswordField();
        splitScreen1.add(fieldPanel1);
        splitScreen1.add(userIdField);
        splitScreen1.add(fieldPanel2);
        splitScreen1.add(usernameField);
        splitScreen1.add(fieldPanel3);
        splitScreen1.add(passwordField);

        // Adding buttons for member operations
        removeButton = new JButton("Remove Member");
        addButton = new JButton("Register");
        splitScreen1.add(removeButton);
        splitScreen1.add(addButton);


        // Action listeners for the buttons
        addButton.addActionListener(e -> addMember());
        removeButton.addActionListener(e -> removeMember());

        // Setting up and adding the member table
        Vector<String> columnNames = getColumnNames();
        tableModel = new DefaultTableModel(LibraryDatabase.getMemberDataVector(), columnNames);
        memberTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setBounds(50, 400, 900, 300);
        splitScreen1.add(scrollPane);

        // Adding home button
        ImageIcon homeIcon = new ImageIcon("home.png");
        JButton home = new JButton(homeIcon);
        home.addActionListener(e -> {
            new LibraryHomeScreen();
            dispose();
        });
        midScreen.add(home,BorderLayout.AFTER_LAST_LINE);

        // Make the frame visible
        setVisible(true);
    }

    /**
     * Method for adding a member.
     */
    private void addMember() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User newUser= new User(username, password);
        int generatedUserId = newUser.getUserId();
        boolean success = LibraryDatabase.addUser(new User(username, password));
        if (success) {
            JOptionPane.showMessageDialog(this, "Member added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add member.");
        }
        refreshMemberList();    }

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
