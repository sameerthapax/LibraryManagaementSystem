/**
 * Book class for the library management system.
 */

public class Book {

    int bid; // Book ID
    String bname; // Name of the book
    String genre; // Genre of the book
    int price; // Price of the book
    boolean isIssued; // Flag to indicate if the book is issued


    public Book(int bid, String bname, String genre, int price) {
        this.bid = bid;
        this.bname = bname;
        this.genre = genre;
        this.price = price;
        this.isIssued = false; // By default, the book is not issued.
    }

}
