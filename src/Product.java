public class Product {

    private final String name;
    private int quantity;
    private double price;

    public Product() {
        this.name = "";
    }

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {return quantity;}
    public String getName(){return name;}
    public double getPrice(){return price;}
}
