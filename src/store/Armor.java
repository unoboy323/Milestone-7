package store;

/**
 * Armor is a type of SalableProduct with defense.
 */
public class Armor extends SalableProduct {

    private int defense;

    /**
     * Default constructor needed for JSON.
     */
    public Armor() {
    }

    /**
     * Creates a new armor item.
     *
     * @param name the armor name
     * @param description the armor description
     * @param price the armor price
     * @param quantity the armor quantity
     * @param defense the armor defense amount
     */
    public Armor(String name, String description, double price, int quantity, int defense) {
        super(name, description, price, quantity);
        this.defense = defense;
    }

    /**
     * Gets the armor defense.
     *
     * @return the defense amount
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the armor defense.
     *
     * @param defense the defense amount
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Creates a separate armor copy for the cart or player bag.
     *
     * @return copied armor with quantity 1
     */
    @Override
    public SalableProduct copyForPurchase() {
        return new Armor(getName(), getDescription(), getPrice(), 1, defense);
    }

    /**
     * Displays the armor information.
     */
    @Override
    public void displayProduct() {
        super.displayProduct();
        System.out.println("Defense: " + defense);
        System.out.println();
    }
}