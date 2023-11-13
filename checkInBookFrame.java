import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class checkInBookFrame extends JFrame {

    private JTextField bookIdField;
    private JTextField userIdField;
    private JButton borrowButton;
    private JPanel midScreen;

    public checkInBookFrame() {
        setTitle("Check IN Books");
        setSize(1000, 1100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBackground(Color.red);

        // Frame Title
        JLabel title = new JLabel("Book Check-Ins");
        title.setFont(new Font("Aharoni", Font.BOLD, 58));
        title.setBounds(300, 5, 500, 80);
        add(title);

        // Home Button
        ImageIcon homeIcon = new ImageIcon("home.png");
        JButton home = new JButton(homeIcon);
        home.setBackground(Color.white);
        home.addActionListener(e -> {
            new LibraryHomeScreen();
            dispose();
        });
        home.setBounds(930, 700, 64, 64);
        add(home);
        // Initialize midScreen panel
        midScreen = new JPanel();
        midScreen.setLayout(null); // Using null layout for absolute positioning
        midScreen.setBounds(50, 50, 900, 700);
        midScreen.setBackground(Color.PINK);
        add(midScreen);

        // Make the frame visible
        setVisible(true);
    }
}

