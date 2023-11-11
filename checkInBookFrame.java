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

        // Book ID Input
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setBounds(50, 100, 100, 30);
        midScreen.add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(150, 100, 200, 30);
        midScreen.add(bookIdField);

        // User ID Input
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(50, 150, 100, 30);
        midScreen.add(userIdLabel);

        userIdField = new JTextField();
        userIdField.setBounds(150, 150, 200, 30);
        midScreen.add(userIdField);

        // Borrow Button
        borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(150, 200, 200, 30);
        borrowButton.addActionListener(e -> borrowBook());
        midScreen.add(borrowButton);






        setVisible(true);
    }

    private void borrowBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            String username = userIdField.getText();

            // Assuming LibraryDatabase has the necessary methods
            if (LibraryDatabase.isBookAvailable(bookId) && LibraryDatabase.isUserValid(username)) {
                LocalDate returnDate = LocalDate.now().plusDays(14); // 14-day borrowing period
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LibraryDatabase.issueBook(username, bookId, LocalDate.now().format(formatter), Integer.parseInt(returnDate.format(formatter)));
                JOptionPane.showMessageDialog(this, "Book borrowed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Book is not available or user ID is invalid.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid IDs.");
        }
    }
}
