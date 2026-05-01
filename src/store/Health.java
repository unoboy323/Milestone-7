package store;

/**
 * Health is a type of SalableProduct that restores health.
 */
public class Health extends SalableProduct {

    private int healingAmount;

    /**
     * Default constructor needed for JSON.
     */
    public Health() {
    }

    /**
     * Creates a new health item.
     *
     * @param name the health item name
     * @param description the health item description
     * @param price the health item price
     * @param quantity the health item quantity
     * @param healingAmount the amount of health restored
     */
    public Health(String name, String description, double price, int quantity, int healingAmount) {
        super(name, description, price, quantity);
        this.healingAmount = healingAmount;
    }

    /**
     * Gets the healing amount.
     *
     * @return the healing amount
     */
    public int getHealingAmount() {
        return healingAmount;
    }

    /**
     * Sets the healing amount.
     *
     * @param healingAmount the healing amount
     */
    public void setHealingAmount(int healingAmount) {
        this.healingAmount = healingAmount;
    }

    /**
     * Creates a separate health item copy for the cart or player bag.
     *
     * @return copied health item with quantity 1
     */
    @Override
    public SalableProduct copyForPurchase() {
        return new Health(getName(), getDescription(), getPrice(), 1, healingAmount);
    }

    /**
     * Displays the health item information.
     */
    @Override
    public void displayProduct() {
        super.displayProduct();
        System.out.println("Healing Amount: " + healingAmount);
        System.out.println();
    }
}