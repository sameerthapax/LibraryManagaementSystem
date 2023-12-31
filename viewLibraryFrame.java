import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Frame to view the books in the Library
 */
public class viewLibraryFrame extends JFrame {
    private JPanel frame;
    private JPanel frame2;
    JPanel controlPanel;
    JButton btnHome;
    private JTable table;
    private DefaultTableModel tableModel;
    String searchText;

    // Constructor fot the viewLibrary frame.
    public viewLibraryFrame() {


        // Setup the main window (JFrame)
        setTitle("Library View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1100);
        //centering frame
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        frame= new JPanel();
        add(frame);
        frame.setBounds(50, 120, 900, 600);
        frame.setLayout(new BorderLayout());
        setVisible(true);

        frame2 = new JPanel();
        JLabel title = new JLabel("Books View");
        title.setFont(new Font("Aharoni", Font.BOLD, 58));
        title.setBounds(300, 5, 500, 80);
        frame2.add(title);
        add(frame2,BorderLayout.PAGE_START);



        // Setup the table model
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] {"ID", "Title", "Genre", "Price","Borrowed"});



        // Setup the table
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        //getting home icon
        ImageIcon homeIcon = new ImageIcon("home.png");

        // Setup the control panel for sorting and searching
        controlPanel = new JPanel();
        btnHome = new JButton("Home",homeIcon);
        JButton btnSortTitle = new JButton("Sort by Title");
        JButton btnSortGenre = new JButton("Sort by Genre");
        JButton btnSortPrice = new JButton("Sort by Price");
        JTextField searchField = new JTextField(40);
        searchField.setText("Search Here!");

        controlPanel.add(btnSortTitle);
        controlPanel.add(btnSortGenre);
        controlPanel.add(btnSortPrice);
        controlPanel.add(searchField);

        frame.add(controlPanel, BorderLayout.BEFORE_FIRST_LINE);
        frame.add(btnHome,BorderLayout.PAGE_END);







        // Add action listeners for buttons
        btnHome.addActionListener(e -> {
            new LibraryHomeScreen();
            dispose();
        });
        btnSortTitle.addActionListener(e -> sortBooksByTitle());
        btnSortGenre.addActionListener(e -> sortBooksByGenre());
        btnSortPrice.addActionListener(e -> sortBooksByPrice());
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(searchField.getText().equals("Search Here!")){
                    searchField.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                searchText = searchField.getText().toLowerCase();
                searchBooks(searchText);
            }
        });

        // Display all books initially
        displayAllBooks();

        // Show the window
        frame.setVisible(true);
    }
    
    //displays all the book in the library
    private void displayAllBooks() {
        updateTable(LibraryDatabase.getBooks().values());
    }
    
    //sort the books in alphabetical order by the title name.
    private void sortBooksByTitle() {
        List<Book> sortedBooks = new ArrayList<>(LibraryDatabase.getBooks().values());
        sortedBooks.sort((o1, o2) -> o1.bname.compareToIgnoreCase(o2.bname));
        updateTable(sortedBooks);
    }

    //sorts the books alphabetically by genre.
    private void sortBooksByGenre() {
        List<Book> sortedBooks = new ArrayList<>(LibraryDatabase.getBooks().values());
        sortedBooks.sort((o1, o2) -> o1.genre.compareToIgnoreCase(o2.genre));
        updateTable(sortedBooks);
    }

    //sorts the books by price in ascending order.
    private void sortBooksByPrice() {
        List<Book> sortedBooks = new ArrayList<>(LibraryDatabase.getBooks().values());
        sortedBooks.sort(Comparator.comparingInt(   o -> o.price));
        updateTable(sortedBooks);
    }

    //Searches the books based on the name or the genre
    private void searchBooks(String searchText) {
        List<Book> matchedBooks = new ArrayList<>();
        for (Book book : LibraryDatabase.getBooks().values()) {
            if (book.bname.toLowerCase().contains(searchText) || book.genre.toLowerCase().contains(searchText)) {
                matchedBooks.add(book);
            }
        }
        updateTable(matchedBooks);
    }

    //updates the table
    private void updateTable(Iterable<Book> books) {
        tableModel.setRowCount(0); // Clear existing data
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.bid, book.bname, book.genre, book.price,book.isIssued});
        }
    }

}

