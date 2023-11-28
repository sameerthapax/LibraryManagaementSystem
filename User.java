
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//User class for the library management system.
public class User {
    private static final AtomicInteger count = new AtomicInteger(10000); // For unique user IDs
    private final int userId;
    private String username;
    private String password; // In a real application, this should be a hashed password
    private String phoneNumber;
    private float sessionId; // Session ID represented by a random float
    private String role; // New field for user role


    /**
     * Constructor to create a new user with userId, username, and password.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password, String phoneNumber, String role) {
        this.userId = count.incrementAndGet(); // Assign and increment the user ID
        this.username = username;
        this.password = password;
        this.phoneNumber= phoneNumber;
        this.sessionId = generateRandomFloat(); // Generate a random float for the session ID
        this.role = role; // Initialize role
    }
    // Generate a random float for session ID
    private float generateRandomFloat() {
        Random random = new Random();
        return random.nextFloat();

    }
    // Getter for session ID
    public float getSessionId() {
        return sessionId;
    }

    //Getter for user ID
    public int getUserId() {
        return userId;
    }

    //Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
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



    public String toString() {
        return "User ID: " + userId + ", Username: " + username;
    }

}
