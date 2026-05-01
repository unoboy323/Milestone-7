package store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Manages all products in the store inventory.
 */
public class InventoryManager {

    private ArrayList<SalableProduct> products;
    private FileService fileService;

    /**
     * Creates an empty inventory manager.
     */
    public InventoryManager() {
        products = new ArrayList<SalableProduct>();
        fileService = new JsonFileService();
    }

    /**
     * Adds a product to inventory.
     *
     * @param product the product to add
     */
    public void addProduct(SalableProduct product) {
        if (product != null) {
            products.add(product);
        }
    }

    /**
     * Removes a product from inventory.
     *
     * @param product the product to remove
     */
    public void removeProduct(SalableProduct product) {
        products.remove(product);
    }

    /**
     * Gets all products in inventory.
     *
     * @return the product list
     */
    public ArrayList<SalableProduct> getProducts() {
        return products;
    }

    /**
     * Initializes the store inventory from a JSON file.
     *
     * @param fileName the inventory file name
     * @throws FileServiceException if the file cannot be read
     */
    public void initializeInventory(String fileName) throws FileServiceException {
        products = fileService.readProducts(fileName);

        if (products == null) {
            products = new ArrayList<SalableProduct>();
        }
    }

    /**
     * Saves the store inventory to a JSON file.
     *
     * @param fileName the inventory file name
     * @throws FileServiceException if the file cannot be written
     */
    public void saveInventory(String fileName) throws FileServiceException {
        fileService.writeProducts(fileName, products);
    }

    /**
     * Reduces the quantity of a product by 1.
     *
     * @param product the product to reduce
     * @return true if the quantity was reduced, false otherwise
     */
    public boolean reduceQuantity(SalableProduct product) {
        if (product == null || product.getQuantity() <= 0) {
            return false;
        }

        product.setQuantity(product.getQuantity() - 1);
        return true;
    }

    /**
     * Increases the quantity of a product by 1.
     *
     * @param product the product to increase
     * @return true if the quantity was increased, false otherwise
     */
    public boolean increaseQuantity(SalableProduct product) {
        if (product == null) {
            return false;
        }

        return increaseQuantityByName(product.getName());
    }

    /**
     * Increases inventory quantity by finding the product name.
     *
     * @param name the product name
     * @return true if the product was found and increased, false otherwise
     */
    public boolean increaseQuantityByName(String name) {
        SalableProduct product = findProduct(name);

        if (product == null) {
            return false;
        }

        product.setQuantity(product.getQuantity() + 1);
        return true;
    }

    /**
     * Finds a product in inventory by name.
     *
     * @param name the product name
     * @return the matching product, or null if not found
     */
    public SalableProduct findProduct(String name) {
        if (name == null) {
            return null;
        }

        String searchName = name.trim();

        for (int i = 0; i < products.size(); i++) {
            SalableProduct product = products.get(i);

            if (product.getName() != null && product.getName().equalsIgnoreCase(searchName)) {
                return product;
            }
        }

        return null;
    }

    /**
     * Resets every product quantity to a random number from 1 to 10.
     */
    public void resetQuantitiesRandomly() {
        Random random = new Random();

        for (int i = 0; i < products.size(); i++) {
            // random number from 1 to 10
            int newQuantity = random.nextInt(10) + 1;
            products.get(i).setQuantity(newQuantity);
        }
    }

    /**
     * Sorts products in ascending order by name and then price.
     */
    public void sortProductsAscending() {
        // uses Comparable from SalableProduct
        Collections.sort(products);
    }

    /**
     * Sorts products in descending order by name and then price.
     */
    public void sortProductsDescending() {
        // first sort ascending
        Collections.sort(products);

        // then reverse the list to make it descending
        Collections.reverse(products);
    }

    /**
     * Sorts products by name and then price.
     */
    public void sortByName() {
        // kept this method so older code still works
        sortProductsAscending();
    }

    /**
     * Displays all products currently in inventory.
     */
    public void displayProducts() {
        // kept this method so older code still works
        displayProductsAscending();
    }

    /**
     * Displays all products in ascending order.
     */
    public void displayProductsAscending() {
        if (products.size() == 0) {
            System.out.println("No products in inventory.");
            return;
        }

        sortProductsAscending();

        System.out.println("\n===== STORE PRODUCTS ASCENDING =====");
        System.out.println("Sorted by name A-Z and then price low-high:\n");

        for (int i = 0; i < products.size(); i++) {
            System.out.println("Product " + (i + 1) + ":");
            products.get(i).displayProduct();
        }
    }

    /**
     * Displays all products in descending order.
     */
    public void displayProductsDescending() {
        if (products.size() == 0) {
            System.out.println("No products in inventory.");
            return;
        }

        sortProductsDescending();

        System.out.println("\n===== STORE PRODUCTS DESCENDING =====");
        System.out.println("Sorted by name Z-A and then price high-low:\n");

        for (int i = 0; i < products.size(); i++) {
            System.out.println("Product " + (i + 1) + ":");
            products.get(i).displayProduct();
        }
    }
}