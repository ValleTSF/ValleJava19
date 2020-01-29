package Database;

import java.util.Date;

public class Out_Of_Stock {
    String id;
    Date date;
    Shoe shoe;

    public Out_Of_Stock(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Shoe getShoe() {
        return shoe;
    }
}
