import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class viewLibraryFrame {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public viewLibraryFrame() {


        // Setup the main window (JFrame)
        frame = new JFrame("Library View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Setup the table model
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] {"ID", "Title", "Genre", "Price","Borrowed"});

        // Setup the table
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Setup the control panel for sorting and searching
        JPanel controlPanel = new JPanel();
        JButton btnSortTitle = new JButton("Sort by Title");
        JButton btnSortGenre = new JButton("Sort by Genre");
        JTextField searchField = new JTextField(10);
        JButton btnSearch = new JButton("Search");

        controlPanel.add(btnSortTitle);
        controlPanel.add(btnSortGenre);
        controlPanel.add(searchField);
        controlPanel.add(btnSearch);
        frame.add(controlPanel, BorderLayout.NORTH);

        // Add action listeners for buttons
        btnSortTitle.addActionListener(e -> sortBooksByTitle());
        btnSortGenre.addActionListener(e -> sortBooksByGenre());
        btnSearch.addActionListener(e -> searchBooks(searchField.getText()));

        // Display all books initially
        displayAllBooks();

        // Show the window
        frame.setVisible(true);
    }

    private void displayAllBooks() {
        updateTable(LibraryDatabase.getBooks().values());
    }

    private void sortBooksByTitle() {
        List<Book> sortedBooks = new ArrayList<>(LibraryDatabase.getBooks().values());
        sortedBooks.sort((o1, o2) -> o1.bname.compareToIgnoreCase(o2.bname));
        updateTable(sortedBooks);
    }

    private void sortBooksByGenre() {
        List<Book> sortedBooks = new ArrayList<>(LibraryDatabase.getBooks().values());
        sortedBooks.sort((o1, o2) -> o1.genre.compareToIgnoreCase(o2.genre));
        updateTable(sortedBooks);
    }

    private void searchBooks(String searchText) {
        List<Book> matchedBooks = new ArrayList<>();
        for (Book book : LibraryDatabase.getBooks().values()) {
            if (book.bname.equalsIgnoreCase(searchText) || book.genre.equalsIgnoreCase(searchText)) {
                matchedBooks.add(book);
            }
        }
        updateTable(matchedBooks);
    }

    private void updateTable(Iterable<Book> books) {
        tableModel.setRowCount(0); // Clear existing data
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.bid, book.bname, book.genre, book.price,book.isIssued});
        }
    }

}

