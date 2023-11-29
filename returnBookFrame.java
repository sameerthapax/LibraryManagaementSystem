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
    private JTextField bookIdField;
    private JPanel body,title1,extraPanel1,extraPanel2,extraPanel3,extraPanel4,extraPanel5,extraPanel6;
    private JLabel returnDateField,title,Bookid,returndate1;
    private JButton returnButton;
    private JTextArea resultArea;
    Font formFieldFont = new Font("Gurmukhi MN", Font.PLAIN, 34);
    Font formFieldFont2 = new Font("Gurmukhi MN", Font.PLAIN, 40);


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
        setLayout(new BorderLayout());
        // Frame Title
        title1 = new JPanel();
        title = new JLabel("Return Book");
        title.setFont(new Font("Aharoni", Font.BOLD, 58));
        title.setBounds(300, 5, 500, 80);

        body = new JPanel(new GridLayout(0,2));

        Bookid = new JLabel("Book Id:");
        returndate1 = new JLabel("Returning Date:");

        extraPanel1 = new JPanel(null);
        extraPanel1.setBackground(Color.PINK);
        extraPanel2 = new JPanel(null);
        extraPanel2.setBackground(Color.PINK);
        extraPanel3 = new JPanel(null);
        extraPanel3.setBackground(Color.PINK);
        extraPanel4 = new JPanel(null);
        extraPanel4.setBackground(Color.PINK);
        extraPanel5 = new JPanel(null);
        extraPanel5.setBackground(Color.PINK);
        extraPanel6 = new JPanel(null);
        extraPanel6.setBackground(Color.PINK);



        bookIdField = new JTextField(20);
        returnDateField = new JLabel(""+LocalDate.now());
        returnButton = new JButton("Return Book");
        resultArea = new JTextArea(5, 30);
        resultArea.setText("                   Payment Area\n\n      Fine Imposed = $--");
        resultArea.setForeground(Color.BLUE);
        resultArea.setBackground(Color.white);
        resultArea.setEditable(false);
    }

    // Organization of the GUI components
    private void layoutComponents() {
        setSize(1000, 1100);
        add(title1,BorderLayout.PAGE_START);
        add(body,BorderLayout.CENTER);
        title1.add(title);
        body.add(extraPanel1);
        body.add(extraPanel2);
        body.add(extraPanel3);
        body.add(extraPanel4);
        body.add(extraPanel5);
        body.add(extraPanel6);

        extraPanel1.add(Bookid);
        Bookid.setBounds(180,10,200,100);
        Bookid.setFont(formFieldFont);

        extraPanel2.add(bookIdField);
        bookIdField.setBounds(10,35,300,50);

        extraPanel3.add(returndate1);
        returndate1.setFont(formFieldFont);
        returndate1.setBounds(70,10,250,100);

        extraPanel4.add(returnDateField);
        returnDateField.setFont(formFieldFont2);
        returnDateField.setBounds(30,10,200,100);
        returnDateField.setForeground(Color.gray);

        extraPanel6.add(returnButton);
        returnButton.setBounds(15,10,300,70);

        add(new JScrollPane(resultArea),BorderLayout.LINE_END);
        JButton homeButton = createHomeButton();
        add(homeButton,BorderLayout.AFTER_LAST_LINE);
        body.setVisible(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }
     // Home Button with an action listener to return to the main home page.
    private JButton createHomeButton() {
        ImageIcon homeIcon = new ImageIcon("home.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.addActionListener(e -> {

            new LibraryHomeScreen();
            dispose();
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
