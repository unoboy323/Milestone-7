package store;

import java.util.ArrayList;

/**
 * Stores products the user adds to the cart.
 */
public class ShoppingCart {

    private ArrayList<SalableProduct> items;

    /**
     * Creates an empty shopping cart.
     */
    public ShoppingCart() {
        items = new ArrayList<SalableProduct>();
    }

    /**
     * Adds a product to the cart.
     *
     * @param product the product to add
     */
    public void addItem(SalableProduct product) {
        if (product != null) {
            items.add(product);
        }
    }

    /**
     * Removes a product from the cart.
     *
     * @param product the product to remove
     * @return true if the product was removed, false otherwise
     */
    public boolean removeItem(SalableProduct product) {
        return items.remove(product);
    }

    /**
     * Finds an item in the cart by name.
     *
     * @param name the product name
     * @return the matching cart item, or null if not found
     */
    public SalableProduct findItem(String name) {
        if (name == null) {
            return null;
        }

        String searchName = name.trim();

        for (int i = 0; i < items.size(); i++) {
            SalableProduct product = items.get(i);

            if (product.getName() != null && product.getName().equalsIgnoreCase(searchName)) {
                return product;
            }
        }

        return null;
    }

    /**
     * Gets all items in the cart.
     *
     * @return the cart item list
     */
    public ArrayList<SalableProduct> getItems() {
        return items;
    }

    /**
     * Checks if the cart is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return items.size() == 0;
    }

    /**
     * Removes everything from the cart.
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * Displays the cart contents.
     */
    public void displayCart() {
        if (items.size() == 0) {
            System.out.println("\n===== SHOPPING CART =====");
            System.out.println("Your cart is empty.");
            System.out.println();
            return;
        }

        System.out.println("\n===== SHOPPING CART =====");
        System.out.println("Items in your cart:");

        for (int i = 0; i < items.size(); i++) {
            SalableProduct product = items.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
        }

        System.out.println("Total items in cart: " + items.size());
        System.out.println();
    }
}