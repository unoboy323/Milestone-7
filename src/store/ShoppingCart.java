package store;

import java.util.ArrayList;

/**
 * Stores products the user adds to the cart.
 */
public class ShoppingCart {

//this makes a var list it stores the salableProduct objects in the cart
    private ArrayList<SalableProduct> items;

//makes an empty shopping cart when the cart obj is created
    public ShoppingCart() {
        items = new ArrayList<SalableProduct>();
    }

//Adds product to cart
    public void addItem(SalableProduct product) {
        items.add(product);
    }

// remove product from cart
    public void removeItem(SalableProduct product) {
        items.remove(product);
    }

//returns the ALL products in cart
    public ArrayList<SalableProduct> getItems() {
        return items;
    }
    
// removes everything  from cart
    public void clearCart() {
        items.clear();
    }
    
// if cart has no items says cart is empty
    public void displayCart() {
        if (items.size() == 0) {
            System.out.println("\n===== SHOPPING CART =====");
            System.out.println("Your cart is empty.");
            System.out.println();
            return;
        }
        
     // if cart has  items it loops through showing each 
        System.out.println("\n===== SHOPPING CART =====");
        System.out.println("Items in your cart:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }

        // added to help the user quickly see how many items are in the cart
        System.out.println("Total items in cart: " + items.size());
        System.out.println();
    }
}