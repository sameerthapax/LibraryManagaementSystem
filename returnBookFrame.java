import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


/**
 * Class representing the interface for returning books in the library management system.
 */
public class returnBookFrame extends JFrame {
    private static final double DAILY_FINE_RATE = 0.50;
    private JTextField bookIdField, returnDateField;
    private JButton returnButton;
    private JTextArea resultArea;

    // Constructor to initialize the components of the return book frame.
    public returnBookFrame() {
        initializeComponents();
        layoutComponents();
        addActionListeners();
    }

    // Initialize GUI components 
    private void initializeComponents() {
        setTitle("Return Book");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        bookIdField = new JTextField(20);
        returnDateField = new JTextField(20);
        returnButton = new JButton("Return Book");
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
    }

    // Organization of the GUI components
    private void layoutComponents() {
        add(new JLabel("Book ID:"));
        add(bookIdField);
        add(new JLabel("Return Date (YYYY-MM-DD):"));
        add(returnDateField);
        add(returnButton);
        add(new JScrollPane(resultArea));
        JButton homeButton = createHomeButton();
        add(homeButton);
        setVisible(true);
        setLocationRelativeTo(null);
    }
     // Home Button with an action listener to return to the main home page.
    private JButton createHomeButton() {
        ImageIcon homeIcon = new ImageIcon("home.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.addActionListener(e -> {
            dispose();
            new LibraryHomeScreen();
        });
        return homeButton;
    }
    
    // action listener to the return button for processing book return.
    private void addActionListeners() {
        returnButton.addActionListener(e -> returnBook());
    }
     
    //Process the return of books based on the user's input
    private void returnBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            LocalDate returnDate = LocalDate.parse(returnDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
            processReturn(bookId, returnDate);
        } catch (NumberFormatException | DateTimeParseException ex) {
            resultArea.setText("Invalid input: " + ex.getMessage());
        }
    }
    
    //Logic of returning books, includes calculating fines and update the records.
    private void processReturn(int bookId, LocalDate returnDate) {
        LibraryDatabase.IssuedBook issuedBook = LibraryDatabase.getIssuedBookDetails(bookId);
        if (issuedBook == null) {
            resultArea.setText("Book not found or not issued.");
        } else {
            String username = issuedBook.username;
            User user = LibraryDatabase.getUsers().get(username);
            if (user == null) {
                resultArea.setText("Invalid user.");
            } else {
                int userId = user.getUserId();
                long daysOverdue = ChronoUnit.DAYS.between(LocalDate.parse(issuedBook.returnDate, DateTimeFormatter.ISO_LOCAL_DATE), returnDate);
                double fine = calculateFine(daysOverdue);
                LibraryDatabase.returnBook(bookId);
                displayReturnInfo(fine);
            }
        }
    }

            
    /**
     * Display return information
     * Success message if the process was successful or not
     * @param fine calculation if overdue
     */    

    private void displayReturnInfo(double fine) {
        resultArea.setText("Book returned successfully.\n");
        if (fine > 0) {
            resultArea.append("Fine imposed: $" + fine);
        } else {
            resultArea.append("No fine imposed.");
        }
    }

    /**
     * Calculate the fine based on the number of days it was overdue.
     * @param daysOverdue The number of days the book was overdue.
     * @return The calculated fine.
     */

    private double calculateFine(long daysOverdue) {
        if (daysOverdue > 0) {
            return daysOverdue * DAILY_FINE_RATE;
        }
        return 0;
    }
}
