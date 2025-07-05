import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        /*NOTES:
        * Assuming no such input: "some_text + space" (these are some of the scanner problems, we can choose another input readers but this is for simplicity)
        * Assuming no number entered instead of text or the vise versa -> gets an Exception.
        *
        * */

        // making some products available on the store products.
        Product chair = new Product("chair", 10, 40);
        Product shoes = new Product("shoes", 1, 170);
        Product gloves = new ExpirableProduct("cheese", 2, 20, LocalDate.of(2020, 3, 12));
        Product helmet = new ShippableProduct("helmet", 4, 60, 100);
        Products.addAvailable(chair);
        Products.addAvailable(gloves);
        Products.addAvailable(helmet);

        // client

        User user = new User("mohamed", 100);

        System.out.println("---------------- Hello " + user.getName() + " ----------------");
        Scanner in = new Scanner(System.in);
        while(true) {

            String choice;
            do{
                System.out.println("Current balance: " + user.getBalance());
                Products.showProducts();
                System.out.println("-> type 'check' to check out");
                System.out.println("-> type 'cart'  to check/edit the cart contents");
                System.out.print("Enter product name >> ");
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

            } else if(choice.equals("cart")) {
                user.cart.showContents();
                System.out.println("Enter product name remove(type 'return' to return):");

                // assuming the user enters the name and quantity right
                String name = in.nextLine();
                if(!name.equals("return")) {
                    int quantity = Integer.parseInt(in.nextLine());
                    int removed = user.cart.removeProductQuantity(name, quantity);
                    if(removed == -1) System.out.println("No such product in the cart!");
                    else System.out.println(removed + " " + name + " Removed from the cart successfully.");

                }



            }else if(Products.isAvailable(choice)) {

                System.out.println("Enter quantity: ");
                int q = Integer.parseInt(in.nextLine());

                Product p = Products.getProduct(choice);
                Flag state = user.addToCart(choice, q);
                if(state.equals(Flag.ADDED_TO_CART)) System.out.println("Product added to cart.");
                else System.out.println("No enough quantity");


            } else {
                System.out.println("Invalid choice.\n\n");
            }









        }








    }
}