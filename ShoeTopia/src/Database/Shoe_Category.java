package Database;

public class Shoe_Category {
    Shoe shoe;
    Category category;

    public Shoe_Category(Shoe shoe) {
        this.shoe = shoe;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public Category getCategory() {
        return category;
    }
}
