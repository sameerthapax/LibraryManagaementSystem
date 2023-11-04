import javax.swing.*;
import java.awt.*;

public class LibraryHomeScreen extends JFrame {

    public LibraryHomeScreen() {
        // Set the title of the window
        setTitle("Library Management System");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,1100);

        // Set the layout
        setLayout(new BorderLayout());


        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Add menus to the menu bar
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        // Add items to the file menu
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        // Add action listener to menu items
        exitItem.addActionListener(e -> System.exit(0));

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        // Set the menu bar
        setJMenuBar(menuBar);
        ImageIcon manageBooksIcon = new ImageIcon("/Users/sams/Desktop/MSU/Fourth Sem/CSC325/1/source image/briefcase.png");
        // Create buttons for different actions
        JButton btnManageBooks = new JButton("Manage Books", manageBooksIcon);
        Font itemsFont = new Font("Monaco",Font.BOLD,14);
        btnManageBooks.setFont(itemsFont);
        btnManageBooks.setIconTextGap(20);


        ImageIcon manageMembersIcon = new ImageIcon("/Users/sams/Desktop/MSU/Fourth Sem/CSC325/1/source image/user.png");

        JButton btnManageMembers = new JButton("Manage Members", manageMembersIcon);
        btnManageMembers.setFont(itemsFont);
        btnManageMembers.setIconTextGap(20);

        ImageIcon checkInBookIcon = new ImageIcon("/Users/sams/Desktop/MSU/Fourth Sem/CSC325/1/source image/check.png");

        JButton btnCheckInBook = new JButton("Check In Book",checkInBookIcon);
        btnCheckInBook.setFont(itemsFont);
        btnCheckInBook.setIconTextGap(20);

        ImageIcon returnBookIcon = new ImageIcon("/Users/sams/Desktop/MSU/Fourth Sem/CSC325/1/source image/return.png");

        JButton btnReturnBook = new JButton("Return Book",returnBookIcon);
        btnReturnBook.setFont(itemsFont);
        btnReturnBook.setIconTextGap(20);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setForeground(Color.PINK);
        buttonPanel.setLayout(new GridLayout(2, 3)); // 2x2 grid with 10px padding

        // Add buttons to the panel
        buttonPanel.add(btnManageBooks);
        buttonPanel.add(btnManageMembers);
        buttonPanel.add(btnCheckInBook);
        buttonPanel.add(btnReturnBook);
        btnManageBooks.addActionListener(e -> {openManageBooks();
            dispose();});
        btnManageMembers.addActionListener(e -> {openManageMembers();
            dispose();});
        btnCheckInBook.addActionListener(e -> {openCheckInBook();
            dispose();});
        btnReturnBook.addActionListener(e -> {openReturnBook();
            dispose();});


        // Add the button panel to the center of the layout
        add(buttonPanel, BorderLayout.CENTER);

        // Pack the frame to fit the preferred size of its subcomponents


        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }
    private void openManageBooks() {
        new manageBooksFrame(); // Open the Manage Books frame

    }

    private void openManageMembers() {
        new manageMembersFrame();
    }// Open the Manage Members frame


    private void openCheckInBook() {
        new checkInBookFrame();
    }// Open the Check In Book frame


    private void openReturnBook() {
        new returnBookFrame();
    }// Open the Return Book frame



}

