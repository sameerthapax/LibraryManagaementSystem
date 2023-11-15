import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTextField;

public class LibraryDatabase {
    private static int userCount = 0;
    private static int bookCount = 0;
    private static int issuedCount = 0;

    private static final Map<String, User> users = new HashMap<>();
    private static final Map<Integer, Book> books = new HashMap<>();
    private static final Map<Integer, IssuedBook> issuedBooks = new HashMap<>();
    static generateRandomBookId bookId1 = new generateRandomBookId();
    static {
        // Add default admin user
        addUser(new User("admin", "admin"));
    }


    public static boolean addUser(User user) {
        users.put(user.getUsername(), user); // Store user by username
        return true;
    }

    public static boolean authenticate(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password); // Compare password
    }




    public static void create() {
    addBook(new Book(bookId1.generaterandombook(), "1984", "Dystopian", 15));
    addBook(new Book(bookId1.generaterandombook(), "To Kill a Mockingbird", "Classic", 12));
    addBook(new Book(bookId1.generaterandombook(), "The Great Gatsby", "Classic", 10));
    }


    public static void addBook(Book book) {
        books.put(book.bid, book);
    }

    public static Map<Integer, Book> getBooks() {
        return books;
    }

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
            // This could be due to invalid user/book ID, or the book is already issued
            throw new IllegalStateException("Book cannot be issued.");
        }
    }

    public static IssuedBook getIssuedBookDetails(int bookId) {
        return issuedBooks.get(bookId);
    }

    public static boolean isBookAvailable(int bookId) {
        Book book = books.get(bookId);
        return book != null && !book.isIssued;
    }

    public static boolean isUserValid(String userId) {
        return users.containsKey(userId);
    }

    public static Map<Integer, User> getUsers() {
        return null;
    }

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


    }

    public static boolean updateUsername(int userId, String  oldUsername, String newUsername) {
        User user = users.remove(oldUsername);
        if (user != null) {
            user.setUsername(newUsername);
            users.put(newUsername, user);
            return true;
        }
        return false;
    
    }

    public static boolean updatePassword(int userId, String newPassword, String username) {
        User user = users.get(username);
        if (user != null) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public static boolean removeUser(int userId, String username, String newPassword) {
        User user = users.remove(username);
        return user != null;  
    }

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

        // Getters
        public String getUsername() {
            return username;
        }

        public int getBookId() {
            return bookId;
        }

        public String getIssuedDate() {
            return issuedDate;
        }

        public String getReturnDate() {
            return returnDate;
        }

        // Setters
        public void setUsername(String username) {
            this.username = username;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public void setIssuedDate(String issuedDate) {
            this.issuedDate = issuedDate;
        }

        public void setReturnDate(String returnDate) {
            this.returnDate = returnDate;
        }
    }

    public static boolean removeUser(int userId) {
        return false;
    }
}
   
