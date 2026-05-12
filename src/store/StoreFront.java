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
    private ServerThread serverThread;

    private static final String INVENTORY_FILE = "inventory.json";

    /**
     * Creates a store front with inventory, cart, bag, and checkout objects.
     */
    public StoreFront() {
        inventory = new InventoryManager();
        cart = new ShoppingCart();
        bag = new PlayerBag();
        checkout = new Checkout();
    }

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

    /**
     * Starts the administration server in the background.
     */
    public void startAdminServer() {
        serverThread = new ServerThread(inventory, INVENTORY_FILE);
        serverThread.start();
    }

    /**
     * Displays all available store products.
     */
    public void displayProducts() {
        inventory.displayProducts();
    }

    /**
     * Displays products in ascending order.
     */
    public void displayProductsAscending() {
        inventory.displayProductsAscending();
    }

    /**
     * Displays products in descending order.
     */
    public void displayProductsDescending() {
        inventory.displayProductsDescending();
    }

    /**
     * Displays the shopping cart.
     */
    public void displayCart() {
        cart.displayCart();
    }

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

    /**
     * Resets all inventory quantities and saves the inventory file.
     */
    public void resetInventoryForNextGame() {
        // gives each item a new random quantity before closing
        inventory.resetQuantitiesRandomly();
        saveInventory();
    }

    /**
     * Adds a product to the cart by name.
     *
     * @param name the product name
     */
    public void addProductToCart(String name) {
        SalableProduct inventoryProduct = inventory.findProduct(name);

        if (inventoryProduct == null) {
            System.out.println("Product not found.");
            System.out.println("Please check the spelling and try again.");
            return;
        }

        if (!inventory.reduceQuantity(inventoryProduct)) {
            System.out.println("Sorry, that product is out of stock.");
            return;
        }

        cart.addItem(inventoryProduct.copyForPurchase());
        saveInventory();

        System.out.println("Product added to cart: " + inventoryProduct.getName());
        System.out.println("You can view your cart from the main menu.");
    }

    /**
     * Cancels a purchase and restores inventory.
     *
     * @param name the product name
     */
    public void cancelPurchase(String name) {
        SalableProduct foundItem = cart.findItem(name);

        if (foundItem == null) {
            System.out.println("That item is not in your cart.");
            System.out.println("Try viewing your cart first to check the item name.");
            return;
        }

        cart.removeItem(foundItem);

        boolean inventoryUpdated = inventory.increaseQuantityByName(foundItem.getName());

        if (!inventoryUpdated) {
            inventory.addProduct(foundItem.copyForPurchase());
        }

        saveInventory();

        System.out.println("Purchase canceled for: " + foundItem.getName());
    }

    /**
     * Processes checkout.
     */
    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        boolean completed = checkout.processCheckout(cart, bag);

        if (completed) {
            System.out.println("Checkout complete.");
            System.out.println("Your items have been moved to your player bag.");
            bag.displayBag();
        }
    }

    /**
     * Main method to run the store.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        StoreFront store = new StoreFront();
        store.initializeStore();
        store.startAdminServer();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("Welcome to the Arena Store Front!");
        System.out.println("Use the menu below to view products, add items to your cart,");
        System.out.println("cancel a purchase, or checkout when you are ready.\n");

        while (choice != 7) {
            System.out.println("===]zzzzzz> || ===]zzzzzz> || ===]zzzzzz>");
            System.out.println("|||Welcome to the Arena Store Front|||");
            System.out.println("1. View Products Ascending");
            System.out.println("2. View Products Descending");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Cancel Purchase");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.println("===]zzzzzz> || ===]zzzzzz> || ===]zzzzzz>");
            System.out.print("Enter your choice (1-7): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input.");
                System.out.println("Please enter a number from 1 to 7.\n");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) {
                // shows products sorted A-Z
                store.displayProductsAscending();
            } else if (choice == 2) {
                // shows products sorted Z-A
                store.displayProductsDescending();
            } else if (choice == 3) {
                System.out.println("\nType the name of the product exactly as shown in the product list.");
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                store.addProductToCart(name);
            } else if (choice == 4) {
                System.out.println("\nEnter the name of the item you want to remove from your cart.");
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                store.cancelPurchase(name);
            } else if (choice == 5) {
                store.displayCart();
            } else if (choice == 6) {
                store.checkout();
            } else if (choice == 7) {
                // reset quantities before closing the program
                store.resetInventoryForNextGame();

                System.out.println("Inventory quantities have been reset for next time.");
                System.out.println("Goodbye.");
                System.out.println("Thanks for visiting the Arena Store Front!");

                // closes the whole program, including the server thread
                System.exit(0);
            } else {
                System.out.println("Invalid menu choice.");
                System.out.println("Please choose a number from 1 to 7.\n");
            }
        }

        scanner.close();
    }
}