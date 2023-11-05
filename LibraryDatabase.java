// This class includes user management, book management, and the issue and return of books.
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

class User {
    int uid;
    String username;
    String password;
    boolean admin;

    public User(int uid, String username, String password, boolean admin) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
}

class Book {
    int bid;
    String bname;
    String genre;
    int price;
    boolean isIssued;

    public Book(int bid, String bname, String genre, int price) {
        this.bid = bid;
        this.bname = bname;
        this.genre = genre;
        this.price = price;
        this.isIssued = false;
    }
}

class IssuedBook {
    int iid;
    int uid;
    int bid;
    String issuedDate;
    String returnDate;
    int period;
    int fine;

    public IssuedBook(int iid, int uid, int bid, String issuedDate, int period) {
        this.iid = iid;
        this.uid = uid;
        this.bid = bid;
        this.issuedDate = issuedDate;
        this.returnDate = null;
        this.period = period;
        this.fine = 0;
    }
}

public class LibraryDatabase {
    private static int userCount = 0;
    private static int bookCount = 0;
    private static int issuedCount = 0;

    private static Map<Integer, User> users = new HashMap<>();
    private static Map<Integer, Book> books = new HashMap<>();
    private static Map<Integer, IssuedBook> issuedBooks = new HashMap<>();

    public static void create() {
        // Add a default admin user
        users.put(++userCount, new User(userCount, "admin", "admin", true));

        // Add some default books
        books.put(++bookCount, new Book(bookCount, "War and Peace", "Mystery", 200));
        books.put(++bookCount, new Book(bookCount, "The Guest Book", "Fiction", 300));
        // ... add more books as needed
    }

    public static void issueBook(int userId, int bookId, String issuedDate, int period) {
        Book book = books.get(bookId);
        if (book == null || book.isIssued) {
            System.out.println("Book is not available for issue.");
            return;
        }

        book.isIssued = true;
        issuedBooks.put(++issuedCount, new IssuedBook(issuedCount, userId, bookId, issuedDate, period));
        System.out.println("Book issued successfully.");
    }

    public static void returnBook(int issuedId, String returnDate) {
        IssuedBook issuedBook = issuedBooks.get(issuedId);
        if (issuedBook == null) {
            System.out.println("Invalid issued book ID.");
            return;
        }

        if (issuedBook.returnDate != null) {
            System.out.println("Book has already been returned.");
            return;
        }

        LocalDate issuedDate = LocalDate.parse(issuedBook.issuedDate);
        LocalDate dueDate = issuedDate.plusDays(issuedBook.period);
        LocalDate actualReturnDate = LocalDate.parse(returnDate);
        long daysLate = ChronoUnit.DAYS.between(dueDate, actualReturnDate);
        int fine = (int) (daysLate > 0 ? daysLate * 10 : 0); // Assuming the fine is $10 per day late

        issuedBook.returnDate = returnDate;
        issuedBook.fine = fine;

        Book book = books.get(issuedBook.bid);
        if (book != null) {
            book.isIssued = false;
        }

        System.out.println("Book returned successfully. Fine: $" + fine);
    }

    public static void main(String[] args) {
        create(); // Initialize the database

        // Example usage
        issueBook(1, 1, "2023-11-01", 14); // User 1 issues Book 1 for 14 days
        returnBook(1, "2023-11-16"); // User 1 returns Book 1 on time (no fine)
    }
}
