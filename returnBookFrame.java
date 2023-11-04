import javax.swing.*;
import java.awt.*;

public class returnBookFrame extends JFrame {
    public returnBookFrame() {
        setTitle("Manage Members");
        setSize(300, 200); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));// Set up the layout
        // Add content to this frame (e.g., a form to add/edit books)
        // For now, we'll just add a label to indicate this is the Manage Books frame
        JLabel label = new JLabel("Return Book management operations will go here", SwingConstants.CENTER);
        JButton home = new JButton("Home");//creating new button for going back to home
        home.addActionListener(e -> {new LibraryHomeScreen();
            dispose();});
        add(home);
        add(label);

        // Make the frame visible
        setVisible(true);
    }
}

