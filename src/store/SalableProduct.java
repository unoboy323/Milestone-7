package store;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a product that can be sold in the store.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Weapon.class, name = "weapon"),
    @JsonSubTypes.Type(value = Armor.class, name = "armor"),
    @JsonSubTypes.Type(value = Health.class, name = "health")
})
public class SalableProduct implements Comparable<SalableProduct> {

    private String name;
    private String description;
    private double price;
    private int quantity;

    /**
     * Default constructor needed for JSON.
     */
    public SalableProduct() {
    }

    /**
     * Creates a new salable product.
     *
     * @param name the product name
     * @param description the product description
     * @param price the product price
     * @param quantity the product quantity
     */
    public SalableProduct(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the product description.
     *
     * @return the product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the product price.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the product quantity.
     *
     * @return the product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the product name.
     *
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the product description.
     *
     * @param description the product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the product price.
     *
     * @param price the product price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the product quantity.
     *
     * @param quantity the product quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Creates a separate copy of the product for the cart or player bag.
     * This prevents cart items from changing the inventory item directly.
     *
     * @return a copied salable product with quantity 1
     */
    public SalableProduct copyForPurchase() {
        return new SalableProduct(name, description, price, 1);
    }

    /**
     * Displays the product information.
     */
    public void displayProduct() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
    }

    /**
     * Compares products by name first and price second.
     *
     * @param other the other product being compared
     * @return comparison result
     */
    @Override
    public int compareTo(SalableProduct other) {
        if (other == null) {
            return 1;
        }

        String thisName = name == null ? "" : name;
        String otherName = other.name == null ? "" : other.name;

        int nameCompare = thisName.compareToIgnoreCase(otherName);

        if (nameCompare != 0) {
            return nameCompare;
        }

        return Double.compare(price, other.price);
    }
}