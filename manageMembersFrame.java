import javax.swing.*;
import java.awt.*;

public class manageMembersFrame extends JFrame {
    public manageMembersFrame() {
        setTitle("Manage Members");
        setSize(1000,1100); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);// Set up the layout
        // Add content to this frame (e.g., a form to add/edit books)
        // For now, we'll just add a label to indicate this is the Manage Books frame
        JLabel label = new JLabel("Members management operations will go here", SwingConstants.CENTER);
        ImageIcon homeIcon = new ImageIcon("/Users/sams/Desktop/MSU/Fourth Sem/CSC325/1/source image/home.png");

        JButton home = new JButton(homeIcon);//creating new button for going back to home
        home.addActionListener(e -> {new LibraryHomeScreen();
            dispose();});
        add(home);
        home.setBounds(930,700,64,64);
        add(label);

        JLabel title = new JLabel("Members Management");
        Font titleFont = new Font("Aharoni", Font.BOLD,58);
        title.setFont(titleFont);

        title.setBounds(170,5,700,80);
        add(title);

        JPanel  midScreen = new JPanel();
        midScreen.setSize(300,300);
        midScreen.setBounds(50,50,900,700);
        midScreen.setBackground(Color.PINK);
        midScreen.setLayout(new GridLayout(2,2));
        JPanel currentUserPanel = new JPanel();


        add(midScreen);

        // Make the frame visible
        setVisible(true);
    }
}

