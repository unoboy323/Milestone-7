package store;

import java.util.ArrayList;

/**
 * Stores items after checkout.
 */
public class PlayerBag {

// lets us store multiple items in a list
    private ArrayList<SalableProduct> items;

// constructor
    public PlayerBag() {
        items = new ArrayList<SalableProduct>();
    }

// adds a product to the player's bag
    public void addItem(SalableProduct product) {
        items.add(product);
    }

// returns the list of items in the bag
    public ArrayList<SalableProduct> getItems() {
        return items;
    }

// shows what items the player has
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
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }

        // added to make the bag display more informative
        System.out.println("Total items in bag: " + items.size());
        System.out.println();
    }
}