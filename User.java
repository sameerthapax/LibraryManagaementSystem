import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger count = new AtomicInteger(0); // For unique user IDs
    private final int userId;
    private String username;
    private String password; // In a real application, this should be a hashed password
    private Set<Integer> borrowedBooks;

    public User(String username, String password) {
        this.userId = count.incrementAndGet(); // Assign and increment the user ID
        this.username = username;
        this.password = password;
        this.borrowedBooks= new HashSet<>();
        
    }

    public void borrowBook(int bookId){
        borrowedBooks.add(bookId);
    }

    public boolean returnBook(int bookId){
        return borrowedBooks.remove(bookId);
    }


    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password; // In a real application, hash the password
    }

    public Set<Integer> getBorrowedBooks() {
        return new HashSet<>(borrowedBooks);
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username;
    }
    
}
