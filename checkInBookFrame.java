import javax.swing.*;
import java.awt.*;

public class checkInBookFrame extends JFrame {
    public checkInBookFrame() {
        setTitle("Manage Members");
        setSize(300, 200); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame on close

        // Add content to this frame (e.g., a form to add/edit books)
        // For now, we'll just add a label to indicate this is the Manage Books frame
        JLabel label = new JLabel("Book check in management operations will go here", SwingConstants.CENTER);
        add(label);

        // Make the frame visible
        setVisible(true);
    }
}

