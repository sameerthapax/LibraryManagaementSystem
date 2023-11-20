import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * For managing books in the library management system.
 */
public class manageBooksFrame extends JFrame {
    JTable bookTable;
    DefaultTableModel tableModel;

    /**
     * Constructor for the manageBooksFrame class.
     * Sets up the GUI components for managing books.
     */
    public manageBooksFrame() {
        setTitle("Manage Books");
        setSize(1000, 1100); // Set the size of the frame
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);        // Add content to this frame (e.g., a form to add/edit books)
        setLayout(null); // Set up the layout

        // A label to indicate this is the Manage Books frame
        JLabel label = new JLabel("Book management operations will go here", SwingConstants.CENTER);

        //Home button
        ImageIcon homeIcon = new ImageIcon("home.png");
        JButton home = new JButton(homeIcon);//creating new button for going back to home
        home.addActionListener(e -> {
            new LibraryHomeScreen();
            dispose(); // Close the current frame
        });
        home.setBounds(930, 700, 64, 64);
        add(label);

        // Title for the frame
        JLabel title = new JLabel("Book Management");
        Font titleFont = new Font("Aharoni", Font.BOLD, 58);
        title.setFont(titleFont);
        title.setBounds(230, 5, 700, 80);
        add(title);

        // Panel for the middle of the screen
        JPanel midScreen = new JPanel(new BorderLayout());
        JPanel splitScreen1 = new JPanel();
        splitScreen1.setOpaque(false);


        midScreen.add(splitScreen1,BorderLayout.CENTER);
        midScreen.add(home,BorderLayout.AFTER_LAST_LINE);

        // Set layout for form elements
        splitScreen1.setLayout(new GridLayout(0, 2, 10, 10)); // Set layout for form elements
        midScreen.setBounds(50, 120, 900, 600);
        midScreen.setBackground(Color.PINK);
        add(midScreen);

        JPanel invisiblePanel1 = new JPanel(null);
        invisiblePanel1.setOpaque(false);

        JPanel invisiblePanel2 = new JPanel(null);
        invisiblePanel2.setOpaque(false);

        JPanel invisiblePanel3 = new JPanel(null);
        invisiblePanel3.setOpaque(false);


        Font formFieldFont = new Font("Gurmukhi MN", Font.PLAIN, 38);
        // Form fields
        JLabel nameLabel = new JLabel("Book Name:");
        nameLabel.setFont(formFieldFont);
        JTextField nameField = new JTextField();
        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setFont(formFieldFont);
        JTextField genreField = new JTextField();
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(formFieldFont);
        JTextField priceField = new JTextField();


        // Add form fields to midScreen

        splitScreen1.add(invisiblePanel1);
        invisiblePanel1.add(nameLabel);
        nameLabel.setBounds(150, 10, 220, 150);
        splitScreen1.add(nameField);

        splitScreen1.add(invisiblePanel2);
        invisiblePanel2.add(genreLabel);
        genreLabel.setBounds(200, 8, 200, 148);
        splitScreen1.add(genreField);

        splitScreen1.add(invisiblePanel3);
        invisiblePanel3.add(priceLabel);
        priceLabel.setBounds(200, 8, 200, 150);
        splitScreen1.add(priceField);

        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("Name");
        columnNames.add("Genre");
        columnNames.add("Price");

        // Initialize the table model and JTable with the book data
        tableModel = new DefaultTableModel(LibraryDatabase.getBookDataVector(), columnNames);
        bookTable = new JTable(tableModel);
        bookTable.setFillsViewportHeight(true);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBounds(50, 250, 900, 300); // Adjust the size as needed
        splitScreen1.add(scrollPane);

        // Submit button for the form
        JButton submitButton = new JButton("Add Book");
        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String genre = genreField.getText();
                int price = Integer.parseInt(priceField.getText()); // Convert price to integer

                // Generate a random 5-digit Book ID
                generateRandomBookId bookId = new generateRandomBookId();

                // Create a new Book object
                Book newBook = new Book(bookId.generaterandomBook(), name, genre, price);

                // Add the book to the database
                LibraryDatabase.addBook(newBook);

                // Clear the text fields after submission
                nameField.setText("");
                genreField.setText("");
                priceField.setText("");

                // Optionally, refresh the book list if you have a component to display it
                refreshBookList(); // This method should be implemented to update the UI
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Add the submit button to the midScreen panel
        splitScreen1.add(submitButton);


        // Make the frame visible
        setVisible(true);
    }
 
    //Refreshes the book list in the Table.
    public void refreshBookList() {
        Vector<Vector<Object>> dataVector = LibraryDatabase.getBookDataVector();
        Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("Name");
        columnNames.add("Genre");
        columnNames.add("Price");

        tableModel.setDataVector(dataVector, columnNames);
    }
}

