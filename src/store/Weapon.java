package store;

/**
 * Weapon is a type of SalableProduct with damage.
 */
public class Weapon extends SalableProduct {

    private int damage;

    /**
     * Default constructor needed for JSON.
     */
    public Weapon() {
    }

    /**
     * Creates a new weapon.
     *
     * @param name the weapon name
     * @param description the weapon description
     * @param price the weapon price
     * @param quantity the weapon quantity
     * @param damage the weapon damage amount
     */
    public Weapon(String name, String description, double price, int quantity, int damage) {
        super(name, description, price, quantity);
        this.damage = damage;
    }

    /**
     * Gets the weapon damage.
     *
     * @return the damage amount
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the weapon damage.
     *
     * @param damage the damage amount
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Creates a separate weapon copy for the cart or player bag.
     *
     * @return copied weapon with quantity 1
     */
    @Override
    public SalableProduct copyForPurchase() {
        return new Weapon(getName(), getDescription(), getPrice(), 1, damage);
    }

    /**
     * Displays the weapon information.
     */
    @Override
    public void displayProduct() {
        super.displayProduct();
        System.out.println("Damage: " + damage);
        System.out.println();
    }
}