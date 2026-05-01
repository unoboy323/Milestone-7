package store;

import java.util.ArrayList;

/**
 * Stores items after checkout.
 */
public class PlayerBag {

    private ArrayList<SalableProduct> items;

    /**
     * Creates an empty player bag.
     */
    public PlayerBag() {
        items = new ArrayList<SalableProduct>();
    }

    /**
     * Adds a product to the player bag.
     *
     * @param product the product to add
     */
    public void addItem(SalableProduct product) {
        if (product != null) {
            items.add(product);
        }
    }

    /**
     * Gets all items in the player bag.
     *
     * @return the player bag item list
     */
    public ArrayList<SalableProduct> getItems() {
        return items;
    }

    /**
     * Displays the player bag contents.
     */
    public void displayBag() {
        if (items.size() == 0) {
            System.out.println("\n===== PLAYER BAG =====");
            System.out.println("Your bag is empty.");
            System.out.println();
            return;
        }

        System.out.println("\n===== PLAYER BAG =====");
        System.out.println("Items in your bag:");

        for (int i = 0; i < items.size(); i++) {
            SalableProduct product = items.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
        }

        System.out.println("Total items in bag: " + items.size());
        System.out.println();
    }
}