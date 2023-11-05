import java.util.Random;

public class generateRandomBookId {
    public int generaterandombook(){
    Random random = new Random();
    int bookId;
        do {
        bookId = 10000 + random.nextInt(90000); // Generates a number between 10000 and 99999
    } while (LibraryDatabase.getBooks().containsKey(bookId)); // Ensure the ID is unique
        return bookId;
    }
}