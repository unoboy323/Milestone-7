package store;

/**
 * Handles moving items from cart to bag.
 */
public class Checkout {

// this method handles the checkout process
    /**
     * Moves items from the cart into the player bag.
     */
    public void processCheckout(ShoppingCart cart, PlayerBag bag) {

        if (cart.getItems().size() == 0) {
            System.out.println("Your cart is empty.");
            return;
        }

        // added so the user knows checkout is happening
        System.out.println("\nProcessing checkout...");

        for (int i = 0; i < cart.getItems().size(); i++) {
            SalableProduct product = cart.getItems().get(i);
            bag.addItem(product);
        }

        cart.clearCart();
    }
}