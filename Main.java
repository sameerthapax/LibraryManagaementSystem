public class Main {
    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // Adding initial users to the LibraryDatabase
        LibraryDatabase.addUser(new User("1", "1", "26029570"));
        LibraryDatabase.addUser(new User("admin", "admin","278457848"));

        // Initialize the LibraryDatabase with some books
        LibraryDatabase.create();

        // Launch the LoginInterface
        new LoginInterface();
    }
}
