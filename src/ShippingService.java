import java.util.ArrayList;

public class ShippingService {

    public static void ship(ArrayList<ShippableProduct> shippedProducts) {

        // nothing to ship
        if(shippedProducts.isEmpty()) return;
        System.out.println("\n** Shipment notice **");
        double totalWeight = 0;
        for(ShippableProduct p : shippedProducts) {
            System.out.printf("%dx %s    %.2fg\n", p.getQuantity(), p.getName(), p.getWeight() * p.getQuantity());
            totalWeight += p.getWeight() * p.getQuantity();
        }

        System.out.printf("Total package weight %.2fkg\n", totalWeight/1000.0);



    }
}
