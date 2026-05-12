package store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main administration application.
 */
public class AdministrationApplication {

    /**
     * Main method to run the admin application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("Welcome to the Arena Store Admin Application.");
        System.out.println("Make sure the Store Front application is running first.\n");

        while (choice != 3) {
            System.out.println("===== ADMIN MENU =====");
            System.out.println("1. View Store Inventory");
            System.out.println("2. Update Store Inventory");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input.");
                System.out.println("Please enter a number from 1 to 3.\n");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                viewInventory(adminService);
            } else if (choice == 2) {
                updateInventory(scanner, adminService);
            } else if (choice == 3) {
                System.out.println("Goodbye from the Admin Application.");
            } else {
                System.out.println("Invalid menu choice.");
                System.out.println("Please choose a number from 1 to 3.\n");
            }
        }

        scanner.close();
    }

    /**
     * Gets inventory from the Store Front and displays it.
     *
     * @param adminService the admin service
     */
    private static void viewInventory(AdminService adminService) {
        try {
            ArrayList<SalableProduct> products = adminService.getInventory();

            System.out.println("\n===== STORE INVENTORY =====");

            if (products.size() == 0) {
                System.out.println("No products in inventory.\n");
                return;
            }

            for (int i = 0; i < products.size(); i++) {
                SalableProduct product = products.get(i);

                System.out.println("Product " + (i + 1) + ":");
                System.out.println("Type: " + product.getClass().getSimpleName());
                System.out.println("Name: " + product.getName());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());

                if (product instanceof Weapon) {
                    Weapon weapon = (Weapon) product;
                    System.out.println("Damage: " + weapon.getDamage());
                } else if (product instanceof Armor) {
                    Armor armor = (Armor) product;
                    System.out.println("Defense: " + armor.getDefense());
                } else if (product instanceof Health) {
                    Health health = (Health) product;
                    System.out.println("Healing Amount: " + health.getHealingAmount());
                }

                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Could not connect to the Store Front.");
            System.out.println("Make sure StoreFront is running first.\n");
        }
    }

    /**
     * Lets the admin create products and send them to the Store Front.
     *
     * @param scanner the keyboard scanner
     * @param adminService the admin service
     */
    private static void updateInventory(Scanner scanner, AdminService adminService) {
        ArrayList<SalableProduct> products = new ArrayList<SalableProduct>();

        String keepGoing = "y";

        while (keepGoing.equalsIgnoreCase("y")) {
            System.out.println("\nChoose product type:");
            System.out.println("1. Weapon");
            System.out.println("2. Armor");
            System.out.println("3. Health");
            System.out.print("Enter choice: ");

            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            if (type == 1) {
                System.out.print("Enter damage: ");
                int damage = scanner.nextInt();
                scanner.nextLine();

                products.add(new Weapon(name, description, price, quantity, damage));
            } else if (type == 2) {
                System.out.print("Enter defense: ");
                int defense = scanner.nextInt();
                scanner.nextLine();

                products.add(new Armor(name, description, price, quantity, defense));
            } else if (type == 3) {
                System.out.print("Enter healing amount: ");
                int healingAmount = scanner.nextInt();
                scanner.nextLine();

                products.add(new Health(name, description, price, quantity, healingAmount));
            } else {
                scanner.nextLine();
                System.out.println("Invalid product type.");
            }

            System.out.print("Add another product? (y/n): ");
            keepGoing = scanner.nextLine();
        }

        try {
            String response = adminService.updateInventory(products);

            if (response.startsWith("OK|")) {
                System.out.println(response.substring(3));
                System.out.println();
            } else {
                System.out.println(response);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Could not connect to the Store Front.");
            System.out.println("Make sure StoreFront is running first.\n");
        }
    }
}