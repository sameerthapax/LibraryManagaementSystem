public class Book {
    static int bid;
    String bname;
    String genre;
    int price;
    boolean isIssued;

    public Book(int bid, String bname, String genre, int price) {
        Book.bid = bid;
        this.bname = bname;
        this.genre = genre;
        this.price = price;
        this.isIssued = false;
    }

    // not sure of Getters and setters
}
