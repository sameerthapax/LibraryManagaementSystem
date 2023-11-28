import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Frame for handeling the book check-ins in the library management system.
 */
public class checkInBookFrame extends JFrame {

    private JTextField bookIdField;
    private JTextField userIdField;
    private JButton borrowButton;
    private JPanel midScreen;

    /**
     * Constructor for CheckInBookFrame.
     * Sets up the UI components for the frame.
     */
    public checkInBookFrame() {
        // Frame setup
        setTitle("Check IN Books");
        setSize(1000, 1100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);


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


        // Initialize midScreen panel
        midScreen = new JPanel();
        midScreen.setLayout(null); // Using null layout for absolute positioning
        midScreen.setBounds(50, 120, 900, 600);
        midScreen.setBackground(Color.PINK);
        add(midScreen);
        home.setBounds(550, 442, 84, 84);
        midScreen.add(home);

        // Book ID Input

        Font formFieldFont = new Font("Gurmukhi MN", Font.PLAIN, 38);
        JLabel bookIdLabel = new JLabel("Book ID:");
        bookIdLabel.setFont(formFieldFont);
        bookIdLabel.setForeground(Color.YELLOW);
        bookIdLabel.setBounds(200, 100, 400, 40);
        midScreen.add(bookIdLabel);

        bookIdField = new JTextField();
        bookIdField.setBounds(400, 78, 400, 80);
        midScreen.add(bookIdField);

        // User ID Input
        JLabel userIdLabel = new JLabel("Username");
        userIdLabel.setFont(formFieldFont);
        userIdLabel.setForeground(Color.YELLOW);
        userIdLabel.setBounds(208, 230, 400, 40);
        midScreen.add(userIdLabel);

        userIdField = new JTextField();
        userIdField.setBounds(400, 210, 400, 80);
        midScreen.add(userIdField);

        // Borrow Button
        borrowButton = new JButton("Borrow Book");
        borrowButton.setBounds(400, 342, 400, 80);
        borrowButton.addActionListener(e -> borrowBook());
        midScreen.add(borrowButton);

        setVisible(true);
    }

    // Variables to store book ID and username
    int bookId;
    String username;

    /**
     * Handles the borrowing of a book.
     * Checks for book availability and user validity before issuing the book.
     */
    private void borrowBook() {
        try {
            bookId = Integer.parseInt(bookIdField.getText());
            username = userIdField.getText();

            // Check book availability and user validity
            if (LibraryDatabase.isBookAvailable(bookId) && LibraryDatabase.isUserValid(username)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LibraryDatabase.issueBook(username, bookId, LocalDate.now().format(formatter), 14);
                JOptionPane.showMessageDialog(this, "Book borrowed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Book is not available or user ID is invalid.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid IDs.");
        }
    }
}
