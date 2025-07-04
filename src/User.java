import java.util.ArrayList;
import java.util.Map;

public class User {

    private String name;
    private double balance;
    public Cart cart;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        cart = new Cart();
    }

    public String getName(){return this.name;}
    public double getBalance(){return this.balance;}


    public Flag checkOut() {
        if(cart.cartStorage.isEmpty()) return Flag.EMPTY_CART;

        double allPrices = cart.getPricesSum() + cart.getShippingCosts();
        if(balance < allPrices) return Flag.INSUFFICIENT_FUNDS;

        if(cart.anyExpired()) return Flag.EXPIRED_PRODUCT;
        if(cart.anyOutOfStock()) return Flag.OUT_OF_STOCK_PRODUCT;


        // info
        double finalDiscount = cart.printReceipt();


        // shipping
        ArrayList<ShippableProduct> ls = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : cart.cartStorage.entrySet()) {

            Product product = Products.getProduct(entry.getKey());

            if(product  instanceof ShippableProduct) {
                ShippableProduct shippableProduct = (ShippableProduct)product;
                ls.add(new ShippableProduct(entry.getKey(), entry.getValue(), product.getPrice(), shippableProduct.getWeight()));
            }

        }

        // shipping all the shippable products.
        ShippingService.ship(ls);

        this.balance -= finalDiscount;
        return Flag.OPERATION_DONE;
    }



    // returns a status which indicates the result of the operation
    public Flag addToCart(String productName, int quantity) {
        if(cart.getAvailableQuantity(productName) < quantity) {
            return Flag.NOT_ENOUGH_QUANTITY;
        }

        // add the product to the current cart
        if(cart.cartStorage.containsKey(productName)) {
            Integer currentQuantity = cart.cartStorage.get(productName);
            Integer newQuantity = currentQuantity + quantity;

            cart.cartStorage.put(productName, newQuantity);
        } else {
            cart.cartStorage.put(productName, quantity);
        }


        return Flag.ADDED_TO_CART;
    }
}
