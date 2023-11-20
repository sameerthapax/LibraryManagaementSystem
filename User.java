import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

//User class for the library management system.
public class User {
    private static final AtomicInteger count = new AtomicInteger(0); // For unique user IDs
    private final int userId;
    private String username;
    private String password; // In a real application, this should be a hashed password
    private Set<Integer> borrowedBooks;

    /**
     * Constructor to create a new user with userId, username, and password.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.userId = count.incrementAndGet(); // Assign and increment the user ID
        this.username = username;
        this.password = password;
        this.borrowedBooks= new HashSet<>();
        
    }

    /**
     * Method to borrow a book, adding the book Id to borrow the book.
     * @param bookId The ID of the book to be borrowed.
     */
    
    public void borrowBook(int bookId){
        borrowedBooks.add(bookId);
    }
    
    //Method to return the book, and also remove the book ID.
    public boolean returnBook(int bookId){
        return borrowedBooks.remove(bookId);
    }

    //Getter for user ID
    public int getUserId() {
        return userId;
    }
    
    //Getter for username
    public String getUsername() {
        return username;
    }
    
    //Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    //Getter for password
    public String getPassword() {
        return password;
    }

    //Setter for password
    public void setPassword(String password) {
        this.password = password; // In a real application, hash the password
    }

    /**
     * Getter for the set of borrowed books.
     * @return A set of book IDs that the user has borrowed.
     */
    public Set<Integer> getBorrowedBooks() {
        return new HashSet<>(borrowedBooks);
    }

    /**@Override toString method to provide string representation of the user.
     * 
     * @return A string representation of the user.
    */
    public String toString() {
        return "User ID: " + userId + ", Username: " + username;
    }
    
}
