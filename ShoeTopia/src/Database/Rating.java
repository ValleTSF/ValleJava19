package Database;

public class Rating {
    String id;
    String score;
    String comment;
    Shoe shoe;
    Customer customer;

    public Rating(String id, String score, String comment, Shoe shoe, Customer customer) {
        this.id = id;
        this.score = score;
        this.comment = comment;
        this.shoe = shoe;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public Customer getCustomer() {
        return customer;
    }
}
