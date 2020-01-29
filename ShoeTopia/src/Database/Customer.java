package Database;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    String id;
    String firstName;
    String lastName;
    City city;
    String password;

    Map<Integer, Orders> ordersMap = new HashMap<>();


    public Customer(String id, String firstName, String lastName, City city, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public City getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public Map<Integer, Orders> getOrdersMap() {
        return ordersMap;
    }
}
