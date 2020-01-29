package Database;

import java.util.Date;

public class Orders {
    String id;
    Date date;
    Customer customer;

    public Orders(String id, Date date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }
}
