import javax.swing.*;
import java.awt.*;

/**
 * Class representing the main home screen of the library management system.
 */
public class LibraryHomeScreen extends JFrame {

    /**
     * Constructor for LibraryHomeScreen.
     * Sets up the UI components for the frame.
     */
    public LibraryHomeScreen() {
        // Frame setup
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1100);
        setLayout(new BorderLayout());

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        // Add items to the file menu
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Set the menu bar
        setJMenuBar(menuBar);

        // Create and configure buttons
        JButton btnManageBooks = createButton("Manage Books", "briefcase.png");
        JButton btnManageMembers = createButton("Manage Members", "user.png");
        JButton btnCheckInBook = createButton("Check In Book", "check.png");
        JButton btnReturnBook = createButton("Return Book", "return.png");
        JButton btnViewBook = createButton("View Library","library.png");

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4)); // 2x3 grid
        buttonPanel.add(btnManageBooks);
        buttonPanel.add(btnManageMembers);
        buttonPanel.add(btnCheckInBook);
        buttonPanel.add(btnReturnBook);
        buttonPanel.add(btnViewBook);

        // Add action listeners to buttons
        addActionListeners(btnManageBooks, btnManageMembers, btnCheckInBook, btnReturnBook,btnViewBook);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Center the frame on the screen and make it visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates a JButton with specified text and icon.
     *

     */
    private JButton createButton(String text, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        JButton button = new JButton(text, icon);
        button.setFont(new Font("Monaco", Font.BOLD, 14));
        button.setIconTextGap(20);
        return button;
    }

    /**
     * Adds action listeners to the buttons for opening different frames.
     *
     * @param btnManageBooks Button to open Manage Books frame.
     * @param btnManageMembers Button to open Manage Members frame.
     * @param btnCheckInBook Button to open Check In Book frame.
     * @param btnReturnBook Button to open Return Book frame.
     */
    private void addActionListeners(JButton btnManageBooks, JButton btnManageMembers, JButton btnCheckInBook, JButton btnReturnBook, JButton btnViewBook) {
        btnManageBooks.addActionListener(e -> {
            openManageBooks();
            dispose();
        });
        btnManageMembers.addActionListener(e -> {
            openManageMembers();
            dispose();
        });
        btnCheckInBook.addActionListener(e -> {
            openCheckInBook();
            dispose();
        });
        btnReturnBook.addActionListener(e -> {
            openReturnBook();
            dispose();
        });
        btnViewBook.addActionListener(e -> {
            openViewBook();
            dispose();
        });
    }

    // Methods to open different frames
    private void openManageBooks() { new manageBooksFrame(); }
    private void openManageMembers() { new manageMembersFrame(); }
    private void openCheckInBook() { new checkInBookFrame(); }
    private void openReturnBook() { new returnBookFrame(); }
    private void openViewBook() { new viewLibraryFrame();}

}
