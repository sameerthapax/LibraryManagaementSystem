import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        LibraryDatabase.addUser(new User("1","1"));
        LibraryDatabase.addUser(new User("admin","admin"));

        LibraryDatabase.create();
        new LoginInterface();
        
    }
}
