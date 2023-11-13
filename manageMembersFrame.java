import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import java.util.Map;
import java.util.Vector;


public class manageMembersFrame extends JFrame {
    private JTextField userIdField, usernameField;
    private JPasswordField passwordField;
    private JButton addButton, updateButton, removeButton;
    private JTextArea memberDetailsArea;
    private JTable memberTable;
    private DefaultTableModel tableModel;


    public manageMembersFrame() {
        setTitle("Manage Members");
        setSize(1000,1100); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);// Set up the layout
        // Add content to this frame (e.g., a form to add/edit books)
        // For now, we'll just add a label to indicate this is the Manage Books frame
        JLabel label = new JLabel("Members management operations will go here", SwingConstants.CENTER);
        ImageIcon homeIcon = new ImageIcon("home.png");

        JButton home = new JButton(homeIcon);//creating new button for going back to home
        home.addActionListener(e -> {new LibraryHomeScreen();
            dispose();});
        add(home);
        home.setBounds(930,700,64,64);
        add(label);

        JLabel title = new JLabel("Members Management");
        Font titleFont = new Font("Aharoni", Font.BOLD,58);
        title.setFont(titleFont);

        title.setBounds(230,5,700,80);
        add(title);

        JPanel  midScreen = new JPanel();
        midScreen.setLayout(new GridLayout(0,2,10,10));
        midScreen.setBounds(50, 155, 900, 600);
        midScreen.setBackground(Color.PINK);
        add(midScreen);

        userIdField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        midScreen.add(new JLabel("User ID:"));
        midScreen.add(userIdField);
        midScreen.add(new JLabel("Username:"));
        midScreen.add(usernameField);
        midScreen.add(new JLabel("Password:")); // Consider using JPasswordField for real applications
        midScreen.add(passwordField);

        addButton = new JButton("Register");
        updateButton = new JButton("Update Member");
        removeButton = new JButton("Remove Member");
        midScreen.add(addButton);
        midScreen.add(updateButton);
        midScreen.add(removeButton);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("User ID");
        columnNames.add("Username");
        

        
        tableModel = new DefaultTableModel(getMemberDataVector(), columnNames);
        memberTable = new JTable(tableModel);
        memberTable.setFillsViewportHeight(true);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setBounds(50, 400, 900, 300); 
        midScreen.add(scrollPane);


        addButton.addActionListener(e -> addMember());
        updateButton.addActionListener(e -> updateMember());
        removeButton.addActionListener(e -> removeMember());
        // Make the frame visible
        setVisible(true);
    }

      private Vector<Vector<Object>> getMemberDataVector() {
        Vector<Vector<Object>> dataVector = new Vector<>();
       return dataVector;
      }
    private void addMember() {
        String username = usernameField.getText();
        String password = passwordField.getText(); // Hash password in real application
        // Assuming LibraryDatabase.addUser() adds a user and returns a boolean
        boolean success = LibraryDatabase.addUser(new User(username, password));
        if (success) {
            JOptionPane.showMessageDialog(this, "Member added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add member.");
        }
    }

  
    private void updateMember() {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            String newUsername = usernameField.getText();
            String newPassword = new String(passwordField.getPassword());

            // Update username and password separately
            boolean usernameUpdated = LibraryDatabase.updateUsername(userId, newUsername);
            boolean passwordUpdated = LibraryDatabase.updatePassword(userId, newPassword);

            if (usernameUpdated && passwordUpdated) {
                memberDetailsArea.setText("Member updated successfully.");
            } else {
                memberDetailsArea.setText("Failed to update member.");
            }
        } catch (NumberFormatException ex) {
            memberDetailsArea.setText("Invalid user ID.");
        }
    }
   

    private void removeMember() {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            boolean success = LibraryDatabase.removeUser(userId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Member removed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove member.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid User ID");
        }
}

    public JTextField getUserIdField() {
        return userIdField;
    }

    public void setUserIdField(JTextField userIdField) {
        this.userIdField = userIdField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(JButton removeButton) {
        this.removeButton = removeButton;
    }

    public JTextArea getMemberDetailsArea() {
        return memberDetailsArea;
    }

    public void setMemberDetailsArea(JTextArea memberDetailsArea) {
        this.memberDetailsArea = memberDetailsArea;
    }

    public JTable getMemberTable() {
        return memberTable;
    }

    public void setMemberTable(JTable memberTable) {
        this.memberTable = memberTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
}

