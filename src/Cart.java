import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    public HashMap<String, Integer> cartStorage;
    public double getAvailableQuantity(String productName) {
        return Products.getAvailableQuantity(productName);
    }

    public boolean anyOutOfStock() {
        for(String pName : this.cartStorage.keySet()) {
            if(Products.isOutOfStock(pName)) {
                return true;
            }
        }
        return false;
    }

    public boolean anyExpired() {
        for(String pName : this.cartStorage.keySet()) {
            if(Products.isExpired(pName)) {
                return true;
            }
        }
        return false;
    }

    public double getShippingCosts() {
        double total = 0;
        for(Map.Entry<String, Integer> e: this.cartStorage.entrySet()) {
            Product p = Products.getProduct(e.getKey());
            if(p instanceof ShippableProduct) total += (e.getValue() * 20);
        }

        return total;
    }

    public double printReceipt() {
        System.out.println("** Checkout receipt **");
        double totalPrice = 0;
        for(Map.Entry<String, Integer> p : cartStorage.entrySet()) {
            Product product = Products.getProduct(p.getKey()); // getting the product info..

            System.out.printf("%dx %s    %.2f\n", p.getValue(), p.getKey(), p.getValue() * product.getPrice());
            totalPrice += product.getPrice() * p.getValue();
        }

        System.out.println("------------------------------");
        double shipCost = getShippingCosts();
        System.out.printf("Subtotal   %.2f\n", totalPrice);
        System.out.printf("Shipping   %.2f\n", shipCost);
        System.out.printf("Amount     %.2f\n", totalPrice + shipCost);



        return totalPrice + shipCost;
    }

    public Cart() {
        this.cartStorage = new HashMap();
    }






    // this is just a weight for testing
    private double getTotalWeight(String name, double quantity) {
        Product product = Products.getProduct(name);
        return quantity * 5;
    }

    public double getPricesSum() {
        double ret = 0;
        for(String name : this.cartStorage.keySet()) {
            Product p = Products.getProduct(name);
            ret += (this.cartStorage.get(name) * p.getPrice());
        }

        return ret;
    }

}
