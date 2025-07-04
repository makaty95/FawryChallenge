import java.time.LocalDate;
import java.util.Date;

public class ExpirableProduct extends Product implements Expirable{


    LocalDate expiryDate;

    public ExpirableProduct(String name, int quantity, double price, LocalDate expiryDate) {
        super(name, quantity, price);
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }
}
