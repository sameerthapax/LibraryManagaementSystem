import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class returnBookFrame extends JFrame {
    private JTextField bookIdField, returnDateField;
    private JButton returnButton;
    private JTextArea resultArea;

  public returnBookFrame() {
        setTitle("Return Book");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Book ID:"));
        bookIdField = new JTextField(20);
        add(bookIdField);

        add(new JLabel("Return Date (YYYY-MM-DD):"));
        returnDateField = new JTextField(20);
        add(returnDateField);

        returnButton = new JButton("Return Book");
        add(returnButton);

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        setVisible(true);
    }

    private void returnBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            LocalDate returnDate = LocalDate.parse(returnDateField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);

            // Assuming LibraryDatabase has a method to get the issued book details
            IssuedBook issuedBook = LibraryDatabase.getIssuedBook(bookId);
            if (issuedBook != null) {
                LocalDate dueDate = LocalDate.parse(issuedBook.getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE);
                long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
                double fine = calculateFine(daysOverdue);

                // Assuming LibraryDatabase has a method to process book return
                LibraryDatabase.returnBook(bookId);

                resultArea.setText("Book returned successfully.\n");
                if (fine > 0) {
                    resultArea.append("Fine imposed: $" + fine);
                } else {
                    resultArea.append("No fine imposed.");
                }
            } else {
                resultArea.setText("Book not found or not issued.");
            }
        } catch (NumberFormatException | DateTimeParseException ex) {
            resultArea.setText("Invalid input.");
        }
    }

    private double calculateFine(long daysOverdue) {
        final double dailyFineRate = 0.50; // Example fine rate
        if (daysOverdue > 0) {
            return daysOverdue * dailyFineRate;
        }
        return 0;
    }

    // Main method for testing
    public static void main(String[] args) {
        new returnBookFrame();
    }
}


