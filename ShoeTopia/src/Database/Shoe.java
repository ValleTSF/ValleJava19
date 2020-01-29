package Database;

public class Shoe {
    String id;
    String name;
    int size;
    String color;
    int price;
    Brand brand;
    int Quantity;

    public Shoe(String id, String name, int size, String color, int price, Brand brand, int quantity) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.brand = brand;
        Quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public Brand getBrand() {
        return brand;
    }

    public int getQuantity() {
        return Quantity;
    }
}
