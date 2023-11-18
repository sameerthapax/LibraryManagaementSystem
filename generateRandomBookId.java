import java.util.Random;

/**
 * Class to generate a random book ID.
 */
public class generateRandomBookId {

    /**
     * Generates a unique random book ID.
     *
     * @return A unique book ID.
     */
    public int generaterandomBook() {
        Random random = new Random();
        int bookId;

        // Loop until a unique ID is generated
        do {
            // Generates a number between 10000 and 99999
            bookId = 10000 + random.nextInt(90000);
        } while (LibraryDatabase.getBooks().containsKey(bookId)); // Ensure the ID is unique

        return bookId;
    }
}
