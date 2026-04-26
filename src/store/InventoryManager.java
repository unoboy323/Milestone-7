package store;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages all products in the store inventory.
 */
public class InventoryManager {

//stores all products available in the store inventory
    private ArrayList<SalableProduct> products;

// file service used to read and write inventory files
    private FileService fileService;
    
//constructor creates an empty inventory list
    public InventoryManager() {
        products = new ArrayList<SalableProduct>();
        fileService = new JsonFileService();
    }

// adds a product to he inventory list 
    public void addProduct(SalableProduct product) {
        products.add(product);
    }

//remove product from inventory 
    public void removeProduct(SalableProduct product) {
        products.remove(product);
    }

// returns the inventory list
    public ArrayList<SalableProduct> getProducts() {
        return products;
    }

// this loads the starting products into inventory
// this is called when the store first starts up
    /**
     * Initializes the store inventory from a JSON file.
     */
    public void initializeInventory(String fileName) throws FileServiceException {
        products = fileService.readProducts(fileName);
    }

// saves the current inventory to the JSON file
// this is used when inventory changes
    /**
     * Saves the store inventory to a JSON file.
     */
    public void saveInventory(String fileName) throws FileServiceException {
        fileService.writeProducts(fileName, products);
    }

// lowers quantity when a product is purchased
// this lets InventoryManager handle inventory changes instead of StoreFront
    /**
     * Reduces the quantity of a product by 1.
     */
    public void reduceQuantity(SalableProduct product) {
        if (product != null && product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
        }
    }

// adds quantity back when a purchase is canceled
// this restores the item amount back into inventory
    /**
     * Increases the quantity of a product by 1.
     */
    public void increaseQuantity(SalableProduct product) {
        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);
        }
    }

// this searches inventory for product name 
    /**
     * Finds a product in inventory by name.
     */
    public SalableProduct findProduct(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)) {
                return products.get(i);
            }
        }
        return null;
    }

    // sorts products by name
    public void sortByName() {
        Collections.sort(products);
    }

// displays all products currently in inventory
    public void displayProducts() {
        if (products.size() == 0) {
            System.out.println("No products in inventory.");
            return;
        }

        sortByName();

        // added to make the inventory section easier to read
        System.out.println("\n===== STORE PRODUCTS =====");
        System.out.println("Here are the items currently available:\n");

        for (int i = 0; i < products.size(); i++) {
            System.out.println("Product " + (i + 1) + ":");
            products.get(i).displayProduct();
        }
    }
    
}