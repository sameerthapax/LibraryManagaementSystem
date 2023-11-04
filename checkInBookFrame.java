import javax.swing.*;
import java.awt.*;

public class checkInBookFrame extends JFrame {
    public checkInBookFrame() {
        setTitle("Check IN Books");
        setSize(1000,1100); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);// Set up the layout
        setBackground(Color.red);
        // Add content to this frame (e.g., a form to add/edit books)
        // For now, we'll just add a label to indicate this is the Manage Books frame
        JLabel label = new JLabel("Book check in management operations will go here", SwingConstants.CENTER);
        ImageIcon homeIcon = new ImageIcon("/Users/sams/Desktop/MSU/Fourth Sem/CSC325/1/source image/home.png");
        JButton home = new JButton(homeIcon);//creating new button for going back to home
        home.setBackground(Color.white);
        home.addActionListener(e -> {new LibraryHomeScreen();
            dispose();});
        add(label);
        add(home);
        home.setBounds(930,700,64,64);

        JLabel title = new JLabel("Book CheckIns");
        Font titleFont = new Font("Aharoni", Font.BOLD,58);
        title.setFont(titleFont);

        title.setBounds(300,5,500,80);
        add(title);

        JPanel  midScreen = new JPanel();
        midScreen.setSize(300,300);
        midScreen.setBounds(50,50,900,700);
        midScreen.setBackground(Color.PINK);

        add(midScreen);

        // Make the frame visible
        setVisible(true);
    }
}

