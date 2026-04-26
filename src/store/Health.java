package store;

/**
 * Health is a type of SalableProduct that restores health.
 */
public class Health extends SalableProduct {

    // extra variable only for health items
    private int healingAmount;

    // default constructor needed for JSON
    public Health() {
    }

    /**
     * Creates a new health item.
     */
    public Health(String name, String description, double price, int quantity, int healingAmount) {
        super(name, description, price, quantity);
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    // setter needed so JSON can update healing amount
    public void setHealingAmount(int healingAmount) {
        this.healingAmount = healingAmount;
    }

    @Override
    public void displayProduct() {
        super.displayProduct();
        System.out.println("Healing Amount: " + healingAmount);
        System.out.println();
    }
}