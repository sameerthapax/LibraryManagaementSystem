public class IssuedBook {
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
