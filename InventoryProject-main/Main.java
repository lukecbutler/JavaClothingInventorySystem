import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {


        // create list of apparel items to be passed into inventory
        ArrayList<Apparel> apparelItems = new ArrayList<>();
        // create inventory object - storing apparel items
        Inventory inventory = new Inventory(apparelItems);



        int checker = 0;

        while(checker == 0) {
            // Main Menu
            Scanner scanner = new Scanner(System.in);
            System.out.println("Add Item to Inventory[1]");
            System.out.println("Display Inventory[2]");
            System.out.println("Delete Item[3]");

            System.out.println("Exit[0]");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter name of product: ");
                    String productName = scanner.nextLine();

                    System.out.println("Enter date product was purchased: ");
                    String date = scanner.nextLine();

                    System.out.println("Enter size of product - if no size enter N/A: ");
                    String size = scanner.nextLine();

                    System.out.println("Enter category of product (Top, Pants, Shoe, Accessory): ");
                    String category = scanner.nextLine();

                    Apparel item = new Apparel(productName, date, size, category);
                    inventory.addItem(item);
                    break;

                case 2:
                    System.out.println(inventory.displayApparelItems());
                    break;
                case 3:

                    System.out.println(inventory.displayApparelItems());
                    System.out.println("Enter the item number you wish to delete: ");
                    inventory.deleteItem(scanner.nextInt());
                    break;
                case 0:
                    checker++;
            }
        }
    }
}
