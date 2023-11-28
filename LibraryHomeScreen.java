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

        Font openFont = new Font("Gurmukhi MN", Font.PLAIN, 38);
        JPanel levelPanel = new JPanel();
        JLabel openText = new JLabel("Hello "+CurrentUser.getCurrentUser().getUsername());
        openText.setFont(openFont);
        levelPanel.add(openText);

        // Create and configure buttons
        JButton btnManageBooks = createButton("Manage Books", "briefcase.png");
        JButton btnManageMembers = createButton("Manage Members", "user.png");
        JButton btnCheckInBook = createButton("Check In Book", "check.png");
        JButton btnReturnBook = createButton("Return Book", "return.png");
        JButton btnViewBook = createButton("View Library","library.png");
        JButton btnLogOut = createButton("Log Out","logout.png");

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4,10,10)); // 2x3 grid
        buttonPanel.add(btnManageBooks);
        buttonPanel.add(btnManageMembers);
        buttonPanel.add(btnCheckInBook);
        buttonPanel.add(btnReturnBook);
        buttonPanel.add(btnViewBook);
        buttonPanel.add(btnLogOut);

        // Add action listeners to buttons
        addActionListeners(btnManageBooks, btnManageMembers, btnCheckInBook, btnReturnBook,btnViewBook,btnLogOut);

        // Add the button panel and level panel to the frame
        add (levelPanel, BorderLayout.PAGE_START);
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

     */
    private void addActionListeners(JButton btnManageBooks, JButton btnManageMembers, JButton btnCheckInBook, JButton btnReturnBook, JButton btnViewBook,JButton btnLogOut) {
        btnManageBooks.addActionListener(e -> {
            if (CurrentUser.getCurrentUser().getRole().equalsIgnoreCase("Admin")){
                openManageBooks();
                dispose();
            }else {
                ImageIcon icon1 = new ImageIcon("lock.png");
                btnManageBooks.setIcon(icon1);
                JOptionPane.showMessageDialog(this, "Requires admin access.");
            }
        });
        btnManageMembers.addActionListener(e -> {
            if (CurrentUser.getCurrentUser().getRole().equalsIgnoreCase("Admin")){
                openManageMembers();
                dispose();
            }else {
                ImageIcon icon1 = new ImageIcon("lock.png");
                btnManageMembers.setIcon(icon1);
                JOptionPane.showMessageDialog(this, "Requires admin access.");
            }
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
        btnLogOut.addActionListener(e -> {
            CurrentUser.logout();
            new LoginInterface();
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
