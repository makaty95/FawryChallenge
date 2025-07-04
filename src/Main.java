import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // making some products available on the store products.
        Product chair = new Product("chair", 10, 40);
        Product gloves = new ExpirableProduct("cheese", 2, 20, LocalDate.of(2020, 3, 12));
        Product helmet = new ShippableProduct("helmet", 4, 60, 100);
        Products.addAvailable(chair);
        Products.addAvailable(gloves);
        Products.addAvailable(helmet);

        // client

        User user = new User("mohamed", 1000);

        System.out.println("---------------- Hello " + user.getName() + " ----------------");
        Scanner in = new Scanner(System.in);
        while(true) {

            String choice;
            do{
                System.out.println("Current balance: " + user.getBalance());
                Products.showProducts();
                System.out.println("type 'check' to check out");
                System.out.print(">> ");
                choice = in.nextLine();
            }while(choice.isEmpty() || choice.isBlank());


            if(choice.equals("check")) {
                Flag state = user.checkOut();
                if(state.equals(Flag.OPERATION_DONE)) {

                    System.out.println("\n\nYour balance after payment: " + user.getBalance());

                    break;

                }
                else if(state.equals(Flag.EMPTY_CART)) System.out.println("The cart in empty!");
                else if(state.equals(Flag.EXPIRED_PRODUCT)) System.out.println("Some products are expired");
                else if(state.equals(Flag.INSUFFICIENT_FUNDS)) System.out.println("No enough balance!");
                else if(state.equals(Flag.OUT_OF_STOCK_PRODUCT)) System.out.println("Some Product are out of stock!");

            } else if(Products.isAvailable(choice)) {

                System.out.println("Enter quantity: ");
                int q = Integer.parseInt(in.nextLine());

                Product p = Products.getProduct(choice);
                Flag state = user.addToCart(choice, q);
                if(state.equals(Flag.NOT_ENOUGH_QUANTITY)) System.out.println("Some products don't have enough quantity");
                else System.out.println("Product added to cart.");

            } else {
                System.out.println("Invalid choice.\n\n");
            }









        }








    }
}