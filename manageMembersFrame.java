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
    JTable bookTable;



    public manageMembersFrame() {
        setTitle("Manage Members");
        setSize(1000,1100); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);// Set up the layout

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

        updateButton = new JButton("Update Member");
        addButton = new JButton("Register");
        removeButton = new JButton("Remove Member");
        midScreen.add(updateButton);
        midScreen.add(addButton);
        midScreen.add(removeButton);

        Vector<String> columnNames = getColumnNames();
        
        
        tableModel = new DefaultTableModel(LibraryDatabase.getMemberDataVector(), columnNames);
        memberTable = new JTable(tableModel);
        memberTable.setFillsViewportHeight(true);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(memberTable);
        scrollPane.setBounds(50, 400, 900, 300); 
        midScreen.add(scrollPane);


        updateButton.addActionListener(e -> updateMember());
        addButton.addActionListener(e -> addMember());
        removeButton.addActionListener(e -> removeMember());
        // Make the frame visible
        setVisible(true);
    }


    private void addMember() {
        String username = usernameField.getText();
        String password = passwordField.getText(); 
        boolean success = LibraryDatabase.addUser(new User(username, password));
        if (success) {
            JOptionPane.showMessageDialog(this, "Member added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add member.");
        }
        refreshMemberList();
    }

  
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
private void updateTable() {
    tableModel.getDataVector().removeAllElements();
    tableModel.fireTableDataChanged();
    Vector<Vector<Object>> newDataVector = LibraryDatabase.getMemberDataVector();
    for (Vector<Object> row : newDataVector) {
        tableModel.addRow(row);
    }
}


private void removeRowFromTable(int userId) {
    for (int i = 0; i < tableModel.getRowCount(); i++) {
        if ((int) tableModel.getValueAt(i, 0) == userId) {
            tableModel.removeRow(i);
            break;
        }
    }
}

public void refreshMemberList() {
        Vector<Vector<Object>> dataVector = LibraryDatabase.getMemberDataVector();
        Vector<String> columnName = new Vector<>();

        columnName.add("User Id");
        columnName.add("Username");

        tableModel.setDataVector(dataVector, columnName );
    }
private Vector<String> getColumnNames() {
    Vector<String> columnNames = new Vector<>();
    columnNames.add("User ID");
    columnNames.add("Username");
    return columnNames;
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

