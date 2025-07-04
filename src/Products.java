import java.util.ArrayList;

public class Products {

    private static ArrayList<Product> storage = new ArrayList<>();
    public Products() {
    }

    public static double getAvailableQuantity(String productName) {
        for(Product p : storage) {
            if(p.getName().equals(productName)) return p.getQuantity();
        }
        return 0;
    }

    public static void showProducts() {
        System.out.println("Product     Available     Price     Shipment");
        for(Product p : storage) {
            System.out.printf("%s          %d         %.2f       %s\n", p.getName(), p.getQuantity(), p.getPrice(), ((p instanceof ShippableProduct )? "YES" : "NO"));
        }
    }

    public static boolean isAvailable(String productName) {
        for(Product p : storage) {
            if(p.getName().equals(productName)) return true;
        }

        return false;
    }



    public static boolean isOutOfStock(String productName) {
        for(Product p : storage) {
            if(p.getName().equals(productName)) {
                if(p.getQuantity() <= 0) return true;
                else break;
            }
        }

        return false;
    }

    public static boolean isExpired(String productName) {

        for(Product p : storage) {
            if(p.getName().equals(productName)) {
                if(p instanceof Expirable) {
                    Expirable expirable = (Expirable)p; // for using checking function
                    if(expirable.isExpired()) return true; else return false;
                } else return false;

            }
        }

        return false;
    }

    public static void addAvailable(Product product) {
        storage.add(product);
    }

    public static Product getProduct(String pName) {
        for(Product p : storage) {
            if(p.getName().equals(pName)) {
                return p;

            }
        }

        return null;
    }
}
