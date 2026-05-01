package store;

/**
 * Handles moving items from the cart to the player bag.
 */
public class Checkout {

    /**
     * Moves items from the cart into the player bag.
     *
     * @param cart the shopping cart
     * @param bag the player bag
     * @return true if checkout was completed, false otherwise
     */
    public boolean processCheckout(ShoppingCart cart, PlayerBag bag) {
        if (cart == null || bag == null || cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return false;
        }

        System.out.println("\nProcessing checkout...");

        for (int i = 0; i < cart.getItems().size(); i++) {
            SalableProduct product = cart.getItems().get(i);
            bag.addItem(product.copyForPurchase());
        }

        cart.clearCart();
        return true;
    }
}