package store;

import java.util.Scanner;

/**
 * Main store front application.
 */
public class StoreFront {

    private InventoryManager inventory;
    private ShoppingCart cart;
    private PlayerBag bag;
    private Checkout checkout;

    // inventory file used to load and save store products
    private static final String INVENTORY_FILE = "inventory.json";

//Constructor when store front OBJ created
    public StoreFront() {
        inventory = new InventoryManager();
        cart = new ShoppingCart();
        bag = new PlayerBag();
        checkout = new Checkout();
    }

// this loads starting product from the inventory file
    /**
     * Initializes store with items from a JSON file.
     */
    
    public void initializeStore() {
        try {
            inventory.initializeInventory(INVENTORY_FILE);
        } catch (FileServiceException e) {
            System.out.println("There was a problem loading the store inventory.");
            System.out.println(e.getMessage());
        }
    }

// displays all available store products
    public void displayProducts() {
        inventory.displayProducts();
    }

// saves inventory after a change happens
    /**
     * Saves the current inventory.
     */
    public void saveInventory() {
        try {
            inventory.saveInventory(INVENTORY_FILE);
        } catch (FileServiceException e) {
            System.out.println("There was a problem saving the store inventory.");
            System.out.println(e.getMessage());
        }
    }

//first searches for product by name 
    /**
     * Adds a product to the cart by name.
     */
    public void addProductToCart(String name) {
        SalableProduct product = inventory.findProduct(name);

        if (product == null) {
            System.out.println("Product not found.");
            System.out.println("Please check the spelling and try again.");
            return;
        }

        if (product.getQuantity() <= 0) {
            System.out.println("Sorry, that product is out of stock.");
            return;
        }

        // inventory manager updates quantity when purchased
        inventory.reduceQuantity(product);
        cart.addItem(product);

        // saves the updated inventory to the JSON file
        saveInventory();

        System.out.println("Product added to cart: " + product.getName());
        System.out.println("You can view your cart from the main menu.");
    }

// this starts with not item found 
    /**
     * Cancels a purchase and restores inventory.
     */
    public void cancelPurchase(String name) {
        SalableProduct foundItem = null;

        for (int i = 0; i < cart.getItems().size(); i++) {
            if (cart.getItems().get(i).getName().equalsIgnoreCase(name)) {
                foundItem = cart.getItems().get(i);
                break;
            }
        }

        if (foundItem == null) {
            System.out.println("That item is not in your cart.");
            System.out.println("Try viewing your cart first to check the item name.");
            return;
        }

        cart.removeItem(foundItem);

        // inventory manager adds quantity back after cancel
        inventory.increaseQuantity(foundItem);

        // saves the updated inventory to the JSON file
        saveInventory();

        System.out.println("Purchase canceled for: " + foundItem.getName());
    }

// this checks if the cart is empty
    /**
     * Processes checkout.
     */
    public void checkout() {
        if (cart.getItems().size() == 0) {
            System.out.println("Your cart is empty.");
            return;
        }

        checkout.processCheckout(cart, bag);

        System.out.println("Checkout complete.");
        System.out.println("Your items have been moved to your player bag.");
        bag.displayBag();
    }

// PROGRAM START
    /**
     * Main method to run the store.
     */
    public static void main(String[] args) {

        StoreFront store = new StoreFront();
        store.initializeStore();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // added welcome text to guide the user when the program starts
        System.out.println("Welcome to the Arena Store Front!");
        System.out.println("Use the menu below to view products, add items to your cart,");
        System.out.println("cancel a purchase, or checkout when you are ready.\n");

        while (choice != 6) {
            System.out.println("===]zzzzzz> || ===]zzzzzz> || ===]zzzzzz>");
            System.out.println("|||Welcome to the Arena Store Front|||");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Cancel Purchase");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.println("===]zzzzzz> || ===]zzzzzz> || ===]zzzzzz>");
            System.out.print("Enter your choice (1-6): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input.");
                System.out.println("Please enter a number from 1 to 6.\n");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                store.displayProducts();
            } else if (choice == 2) {
                System.out.println("\nType the name of the product exactly as shown in the product list.");
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                store.addProductToCart(name);
            } else if (choice == 3) {
                System.out.println("\nEnter the name of the item you want to remove from your cart.");
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                store.cancelPurchase(name);
            } else if (choice == 4) {
                store.cart.displayCart();
            } else if (choice == 5) {
                store.checkout();
            } else if (choice == 6) {
                System.out.println("Goodbye.");
                System.out.println("Thanks for visiting the Arena Store Front!");
            } else {
                System.out.println("Invalid menu choice.");
                System.out.println("Please choose a number from 1 to 6.\n");
            }
        }

        scanner.close();
    }
}