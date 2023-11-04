import javax.swing.*;
import java.awt.*;

public class manageBooksFrame extends JFrame {
    public manageBooksFrame() {
        setTitle("Manage Books");
        setSize(1000,1100); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);        // Add content to this frame (e.g., a form to add/edit books)
        setLayout(null); // Set up the layout

        // For now, we'll just add a label to indicate this is the Manage Books frame
        JLabel label = new JLabel("Book management operations will go here", SwingConstants.CENTER);

        ImageIcon homeIcon = new ImageIcon("/Users/sams/Desktop/MSU/Fourth Sem/CSC325/1/source image/home.png");
        JButton home = new JButton(homeIcon);//creating new button for going back to home
        home.addActionListener(e -> {new LibraryHomeScreen();
        dispose();});
        home.setBounds(930,700,64,64);
        add(home);

        JLabel title = new JLabel("Book Management");
        Font titleFont = new Font("Aharoni", Font.BOLD,58);
        title.setFont(titleFont);

        title.setBounds(230,5,700,80);
        add(title);

        JPanel  midScreen = new JPanel();
        midScreen.setSize(300,300);
        midScreen.setBounds(50,50,900,700);
        midScreen.setBackground(Color.PINK);

        add(midScreen);

        add(label);



        // Make the frame visible
        setVisible(true);
    }
}
