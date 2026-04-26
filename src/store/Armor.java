package store;

/**
 * Armor is a type of SalableProduct with defense.
 */
public class Armor extends SalableProduct {

    // extra variable only for armor
    private int defense;

    // default constructor needed for JSON
    public Armor() {
    }

    /**
     * Creates a new armor item.
     */
    public Armor(String name, String description, double price, int quantity, int defense) {
        super(name, description, price, quantity);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    // setter needed so JSON can update defense
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public void displayProduct() {
        super.displayProduct();
        System.out.println("Defense: " + defense);
        System.out.println();
    }
}