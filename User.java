import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger count = new AtomicInteger(0); // For unique user IDs
    private final int userId;
    private String username;
    private String password; // In a real application, this should be a hashed password

    public User(String username, String password) {
        this.userId = count.incrementAndGet(); // Assign and increment the user ID
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
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
}
