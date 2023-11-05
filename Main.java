import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        new LoginInterface();
        LibraryDatabase.create();
        SwingUtilities.invokeLater(() -> new LibraryHomeScreen());



    }
}
