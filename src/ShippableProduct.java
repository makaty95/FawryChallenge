public class ShippableProduct extends Product implements ShippingInfo  {
    private double weight;

    public ShippableProduct(String name, int quantity, double price, double weight) {
        super(name, quantity, price);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
