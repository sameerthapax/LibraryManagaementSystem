import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class LibraryDatabase {
    private static int userCount = 0;
    private static int bookCount = 0;
    private static int issuedCount = 0;

    private static Map<Integer, User> users = new HashMap<>();
    private static final Map<Integer, Book> books = new HashMap<>();
    private static Map<Integer, IssuedBook> issuedBooks = new HashMap<>();
    static generateRandomBookId bookId1 = new generateRandomBookId();




    public static void create() {
        // Add three default books to the database
        addBook(new Book(bookId1.generaterandombook(), "1984", "Dystopian", 15));
        addBook(new Book(bookId1.generaterandombook(), "To Kill a Mockingbird", "Classic", 12));
        addBook(new Book(bookId1.generaterandombook(), "The Great Gatsby", "Classic", 10));
    }



    public static void addBook(Book book) {
        books.put(Book.bid, book);
    }

    public static Map<Integer, Book> getBooks() {
        return books;
    }
    public static Vector<Vector<Object>> getBookDataVector() {
        Vector<Vector<Object>> dataVector = new Vector<>();
        for (Book book : books.values()) {
            Vector<Object> row = new Vector<>();
            row.add(Book.bid);
            row.add(book.bname);
            row.add(book.genre);
            row.add(book.price);
            dataVector.add(row);
        }
        return dataVector;
    }
    // ... (Include other methods like issueBook, returnBook, getBooks, addBook, etc.)
}

