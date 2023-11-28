public class Main {
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Adding initial users to the LibraryDatabase
        LibraryDatabase.addUser(new User("1", "1", "2602957000","Not Admin"));
        LibraryDatabase.addUser(new User("admin", "admin","0000000000","Admin"));

        // Initialize the LibraryDatabase with some books
        LibraryDatabase.create();

        // Launch the LoginInterface
        new LoginInterface();
    }
}
