package store;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class InventoryManagerTest {

    @Test
    public void testAddProduct() {
        InventoryManager manager = new InventoryManager();
        Weapon weapon = new Weapon("Sword", "Sharp blade", 30.00, 4, 12);

        manager.addProduct(weapon);

        Assert.assertEquals(1, manager.getProducts().size());
        Assert.assertEquals("Sword", manager.getProducts().get(0).getName());
    }

    @Test
    public void testAddNullProductDoesNotAdd() {
        InventoryManager manager = new InventoryManager();

        manager.addProduct(null);

        Assert.assertEquals(0, manager.getProducts().size());
    }

    @Test
    public void testRemoveProduct() {
        InventoryManager manager = new InventoryManager();
        Armor armor = new Armor("Shield", "Strong shield", 15.00, 2, 10);

        manager.addProduct(armor);
        manager.removeProduct(armor);

        Assert.assertEquals(0, manager.getProducts().size());
    }

    @Test
    public void testFindProductIgnoresCaseAndSpaces() {
        InventoryManager manager = new InventoryManager();
        Health health = new Health("Potion", "Small heal", 5.00, 10, 25);

        manager.addProduct(health);

        SalableProduct foundProduct = manager.findProduct("  potion  ");

        Assert.assertNotNull(foundProduct);
        Assert.assertEquals("Potion", foundProduct.getName());
    }

    @Test
    public void testFindProductReturnsNullWhenNotFound() {
        InventoryManager manager = new InventoryManager();

        SalableProduct foundProduct = manager.findProduct("Sword");

        Assert.assertNull(foundProduct);
    }

    @Test
    public void testReduceQuantity() {
        InventoryManager manager = new InventoryManager();
        Weapon weapon = new Weapon("Sword", "Sharp blade", 30.00, 4, 12);

        boolean result = manager.reduceQuantity(weapon);

        Assert.assertTrue(result);
        Assert.assertEquals(3, weapon.getQuantity());
    }

    @Test
    public void testReduceQuantityReturnsFalseWhenQuantityIsZero() {
        InventoryManager manager = new InventoryManager();
        Weapon weapon = new Weapon("Sword", "Sharp blade", 30.00, 0, 12);

        boolean result = manager.reduceQuantity(weapon);

        Assert.assertFalse(result);
        Assert.assertEquals(0, weapon.getQuantity());
    }

    @Test
    public void testIncreaseQuantityByName() {
        InventoryManager manager = new InventoryManager();
        Armor armor = new Armor("Shield", "Strong shield", 15.00, 2, 10);

        manager.addProduct(armor);

        boolean result = manager.increaseQuantityByName("shield");

        Assert.assertTrue(result);
        Assert.assertEquals(3, armor.getQuantity());
    }

    @Test
    public void testIncreaseQuantityByNameReturnsFalseWhenNotFound() {
        InventoryManager manager = new InventoryManager();

        boolean result = manager.increaseQuantityByName("Potion");

        Assert.assertFalse(result);
    }

    @Test
    public void testUpdateInventoryAddsNewProduct() {
        InventoryManager manager = new InventoryManager();

        ArrayList<SalableProduct> newProducts = new ArrayList<SalableProduct>();
        newProducts.add(new Health("Potion", "Small heal", 5.00, 10, 25));

        manager.updateInventory(newProducts);

        Assert.assertEquals(1, manager.getProducts().size());
        Assert.assertEquals("Potion", manager.getProducts().get(0).getName());
    }

    @Test
    public void testUpdateInventoryUpdatesExistingWeapon() {
        InventoryManager manager = new InventoryManager();

        Weapon oldWeapon = new Weapon("Sword", "Old sword", 20.00, 2, 10);
        Weapon newWeapon = new Weapon("Sword", "Updated sword", 30.00, 5, 15);

        manager.addProduct(oldWeapon);

        ArrayList<SalableProduct> newProducts = new ArrayList<SalableProduct>();
        newProducts.add(newWeapon);

        manager.updateInventory(newProducts);

        SalableProduct foundProduct = manager.findProduct("Sword");

        Assert.assertNotNull(foundProduct);
        Assert.assertTrue(foundProduct instanceof Weapon);

        Weapon updatedWeapon = (Weapon) foundProduct;

        Assert.assertEquals("Updated sword", updatedWeapon.getDescription());
        Assert.assertEquals(30.00, updatedWeapon.getPrice(), 0.001);
        Assert.assertEquals(7, updatedWeapon.getQuantity());
        Assert.assertEquals(15, updatedWeapon.getDamage());
    }

    @Test
    public void testSortProductsAscending() {
        InventoryManager manager = new InventoryManager();

        manager.addProduct(new Weapon("Bow", "Long bow", 20.00, 1, 10));
        manager.addProduct(new Weapon("Axe", "Cheap axe", 10.00, 1, 12));
        manager.addProduct(new Weapon("axe", "Expensive axe", 30.00, 1, 18));

        manager.sortProductsAscending();

        Assert.assertEquals("Axe", manager.getProducts().get(0).getName());
        Assert.assertEquals(10.00, manager.getProducts().get(0).getPrice(), 0.001);

        Assert.assertEquals("axe", manager.getProducts().get(1).getName());
        Assert.assertEquals(30.00, manager.getProducts().get(1).getPrice(), 0.001);

        Assert.assertEquals("Bow", manager.getProducts().get(2).getName());
    }

    @Test
    public void testSortProductsDescending() {
        InventoryManager manager = new InventoryManager();

        manager.addProduct(new Weapon("Bow", "Long bow", 20.00, 1, 10));
        manager.addProduct(new Weapon("Axe", "Cheap axe", 10.00, 1, 12));
        manager.addProduct(new Weapon("axe", "Expensive axe", 30.00, 1, 18));

        manager.sortProductsDescending();

        Assert.assertEquals("Bow", manager.getProducts().get(0).getName());

        Assert.assertEquals("axe", manager.getProducts().get(1).getName());
        Assert.assertEquals(30.00, manager.getProducts().get(1).getPrice(), 0.001);

        Assert.assertEquals("Axe", manager.getProducts().get(2).getName());
        Assert.assertEquals(10.00, manager.getProducts().get(2).getPrice(), 0.001);
    }
}