import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class returnBookFrame extends JFrame {
    private static final double DAILY_FINE_RATE = 0.50;
    private JTextField bookIdField, returnDateField;
    private JButton returnButton;
    private JTextArea resultArea;

    public returnBookFrame() {
        initializeComponents();
        layoutComponents();
        addActionListeners();
    }

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

    private JButton createHomeButton() {
        ImageIcon homeIcon = new ImageIcon("home.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.addActionListener(e -> {
            dispose();
            new LibraryHomeScreen();
        });
        return homeButton;
    }

    private void addActionListeners() {
        returnButton.addActionListener(e -> returnBook());
    }

    private void returnBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            LocalDate returnDate = LocalDate.parse(returnDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
            processReturn(bookId, returnDate);
        } catch (NumberFormatException | DateTimeParseException ex) {
            resultArea.setText("Invalid input: " + ex.getMessage());
        }
    }

    private void processReturn(int bookId, LocalDate returnDate) {
        LibraryDatabase.IssuedBook issuedBook = LibraryDatabase.getIssuedBookDetails(bookId);
        if (issuedBook == null) {
            resultArea.setText("Book not found or not issued.");
            return;
        }

        LocalDate dueDate = LocalDate.parse(issuedBook.returnDate, DateTimeFormatter.ISO_LOCAL_DATE);
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
        double fine = calculateFine(daysOverdue);
        LibraryDatabase.returnBook(bookId);

        displayReturnInfo(fine);
    }

    private void displayReturnInfo(double fine) {
        resultArea.setText("Book returned successfully.\n");
        if (fine > 0) {
            resultArea.append("Fine imposed: $" + fine);
        } else {
            resultArea.append("No fine imposed.");
        }
    }

    private double calculateFine(long daysOverdue) {
        if (daysOverdue > 0) {
            return daysOverdue * DAILY_FINE_RATE;
        }
        return 0;
    }
}
