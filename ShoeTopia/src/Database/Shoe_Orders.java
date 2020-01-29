package Database;

public class Shoe_Orders {
    Shoe shoe;
    Orders orders;
    int quantity;
    Boolean completed;

    public Shoe_Orders(Shoe shoe, Orders orders, int quantity, Boolean completed) {
        this.shoe = shoe;
        this.orders = orders;
        this.quantity = quantity;
        this.completed = completed;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public Orders getOrders() {
        return orders;
    }

    public int getQuantity() {
        return quantity;
    }

    public Boolean getCompleted() {
        return completed;
    }
}
