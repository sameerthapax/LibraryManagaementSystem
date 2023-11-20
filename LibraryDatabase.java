import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;

/**
 * Represents the database functionality for a library system.
 */
public class LibraryDatabase {
    // Counters for users, books, and issued books
    private static int userCount = 0;
    private static int bookCount = 0;
    private static int issuedCount = 0;

    // Maps to store users, books, and issued books
    private static final Map<String, User> users = new HashMap<>();
    private static final Map<Integer, Book> books = new HashMap<>();
    private static final Map<Integer, IssuedBook> issuedBooks = new HashMap<>();
    private static generateRandomBookId bookIdGenerator = new generateRandomBookId();

    // Method to add a user
    public static boolean addUser(User user) {
        users.put(user.getUsername(), user); // Store user by username
        return true;
    }

    // Method for user authentication
    public static boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password); // Compare password
    }

    // Method to initialize the database with some books
    public static void create() {
        addBook(new Book(bookIdGenerator.generaterandomBook(), "1984", "Dystopian", 89));
        addBook(new Book(bookIdGenerator.generaterandomBook(), "To Kill a Mockingbird", "Classic", 77));
        addBook(new Book(bookIdGenerator.generaterandomBook(), "The Great Gatsby", "Classic", 90));
        addBook(new Book(bookIdGenerator.generaterandomBook(), "The Power", "Sci-fi", 85));
        addBook(new Book(bookIdGenerator.generaterandomBook(), "Harry Potter and The Cursed Child", "Fantasy", 200));
        addBook(new Book(bookIdGenerator.generaterandomBook(), "The Avengers", "Comic", 100));
        addBook(new Book(bookIdGenerator.generaterandomBook(), "The Spider Man", "Comic", 73));
        addBook(new Book(bookIdGenerator.generaterandomBook(), "Fitness Club", "Magazine", 20));
    }

    // Method to add a book
    public static void addBook(Book book) {
        books.put(book.bid, book);
    }

    // Getter for books map
    public static Map<Integer, Book> getBooks() {
        return books;
    }

    // Method to get a data vector of books for GUI display
    public static Vector<Vector<Object>> getBookDataVector() {
        Vector<Vector<Object>> dataVector = new Vector<>();
        for (Book book : books.values()) {
            Vector<Object> row = new Vector<>();
            row.add(book.bid);
            row.add(book.bname);
            row.add(book.genre);
            row.add(book.price);
            dataVector.add(row);
        }
        return dataVector;
    }

    // Method to get a data vector of members for GUI display
    public static Vector<Vector<Object>> getMemberDataVector() {
        Vector<Vector<Object>> dataVector = new Vector<>();
        for (User user : users.values()) {
            Vector<Object> row = new Vector<>();
            row.add(user.getUserId());
            row.add(user.getUsername());
            dataVector.add(row);
        }
        return dataVector;
    }

    // Method to issue a book
    public static void issueBook(String username, int bookId, String issuedDate, int period) {
        Book book = books.get(bookId);
        User user = users.get(username);

        if (book != null && user != null && !book.isIssued) {
            book.isIssued = true; // Mark the book as issued

            // Calculate return date
            LocalDate returnDate = LocalDate.parse(issuedDate).plusDays(period);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedReturnDate = returnDate.format(formatter);

            // Record the issued book details
            IssuedBook issuedBook = new IssuedBook(username, bookId, issuedDate, formattedReturnDate);
            issuedBooks.put(bookId, issuedBook);
        } else {
            // Handle the case where the book can't be issued
            throw new IllegalStateException("Book cannot be issued.");
        }
    }

    // Method to check if a user is valid
    public static boolean isUserValid(String username) {
        return users.containsKey(username);
    }

    // Getter for users map
    public static Map<String, User> getUsers() {
        return new HashMap<>(users); // Return a copy of the users map
    }

    // Method to return a book
    public static void returnBook(int bookId) {
        IssuedBook issuedBook = issuedBooks.get(bookId);
        if (issuedBook == null) {
            throw new IllegalStateException("Book was not issued.");
        }

        // Mark the book as not issued
        Book book = books.get(bookId);
        if (book != null) {
            book.isIssued = false;
        }
        
        //Remove the issued book record
        issuedBooks.remove(bookId);
    }
    // Method to check if a book is available
public static boolean isBookAvailable(int bookId) {
    Book book = books.get(bookId);
    return book != null && !book.isIssued;
}

// Method to get issued book details
public static IssuedBook getIssuedBookDetails(int bookId) {
    return issuedBooks.get(bookId);
}



    // Method to update user's username
    public static boolean updateUsername(int userId, String newUsername){
        User user = users.get(userId);
        if (user != null){
            user.setUsername(newUsername);
            return true;
        }
        return false;
    }

    // Method to update user's password
    public static boolean updatePassword(int userId, String newPassword){
        User user = users.get(userId);
        if (user != null){
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    // Method to remove a user
    public static boolean removeUser(String username) {
        users.remove(username);
        return true;
    }

    // Method to check if a user ID is valid
    public static boolean isUseridValid(int userId) {
        return users.containsKey(userId);
    }

    /**
     * Inner class representing an issued book.
     */
    static class IssuedBook {
        String username;
        int bookId;
        String issuedDate;
        String returnDate;

        public IssuedBook(String username, int bookId, String issuedDate, String returnDate) {
            this.username = username;
            this.bookId = bookId;
            this.issuedDate = issuedDate;
            this.returnDate = returnDate;
        }


    }
    
}
