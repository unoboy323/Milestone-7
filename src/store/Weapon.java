package store;

/**
 * Weapon is a type of SalableProduct with damage.
 */
public class Weapon extends SalableProduct {

    // extra variable only for weapons
    private int damage;

    // default constructor needed for JSON
    public Weapon() {
    }

    /**
     * Creates a new weapon.
     */
    public Weapon(String name, String description, double price, int quantity, int damage) {
        super(name, description, price, quantity);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    // setter needed so JSON can update damage
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void displayProduct() {
        super.displayProduct();
        System.out.println("Damage: " + damage);
        System.out.println();
    }
}