import javax.swing.*;
import java.awt.*;

public class manageBooksFrame extends JFrame {
    public manageBooksFrame() {
        setTitle("Manage Books");
        setSize(300, 200); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);        // Add content to this frame (e.g., a form to add/edit books)
        setLayout(new GridLayout(3,2)); // Set up the layout

        // For now, we'll just add a label to indicate this is the Manage Books frame
        JLabel label = new JLabel("Book management operations will go here", SwingConstants.CENTER);

        JButton home = new JButton("Home");//creating new button for going back to home
        home.addActionListener(e -> {new LibraryHomeScreen();
        dispose();});


        add(label);

        add(home);

        // Make the frame visible
        setVisible(true);
    }
}
