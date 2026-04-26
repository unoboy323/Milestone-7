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

//Creating the variables
    private String name;
    private String description;
    private double price;
    private int quantity;

// default constructor needed for JSON
    public SalableProduct() {
    }

//Runs when make a new product it give the new product name, price etc
    /**
     * Creates a new salable product.
     */
    public SalableProduct(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    
//Getters methods allows other classes to read
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

//Setter methods allows other classes to update values
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Updates the quantity of the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void displayProduct() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantity);
    }

    // lets products be sorted by name
    // if names are the same, sort by price
    @Override
    public int compareTo(SalableProduct other) {
        int nameCompare = this.name.compareToIgnoreCase(other.name);

        if (nameCompare != 0) {
            return nameCompare;
        }

        return Double.compare(this.price, other.price);
    }
}