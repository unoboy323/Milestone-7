package store;

import org.junit.Assert;
import org.junit.Test;

public class SalableProductTest {

    @Test
    public void testConstructorAndGetters() {
        SalableProduct product = new SalableProduct("Sword", "Basic sword", 25.99, 5);

        Assert.assertEquals("Sword", product.getName());
        Assert.assertEquals("Basic sword", product.getDescription());
        Assert.assertEquals(25.99, product.getPrice(), 0.001);
        Assert.assertEquals(5, product.getQuantity());
    }

    @Test
    public void testSetters() {
        SalableProduct product = new SalableProduct();

        product.setName("Shield");
        product.setDescription("Strong shield");
        product.setPrice(15.50);
        product.setQuantity(3);

        Assert.assertEquals("Shield", product.getName());
        Assert.assertEquals("Strong shield", product.getDescription());
        Assert.assertEquals(15.50, product.getPrice(), 0.001);
        Assert.assertEquals(3, product.getQuantity());
    }

    @Test
    public void testCopyForPurchase() {
        SalableProduct product = new SalableProduct("Potion", "Small potion", 5.00, 10);

        SalableProduct copy = product.copyForPurchase();

        Assert.assertEquals("Potion", copy.getName());
        Assert.assertEquals("Small potion", copy.getDescription());
        Assert.assertEquals(5.00, copy.getPrice(), 0.001);
        Assert.assertEquals(1, copy.getQuantity());
    }

    @Test
    public void testCompareToSortsByNameThenPrice() {
        SalableProduct product1 = new SalableProduct("Axe", "Cheap axe", 10.00, 1);
        SalableProduct product2 = new SalableProduct("axe", "Expensive axe", 20.00, 1);
        SalableProduct product3 = new SalableProduct("Bow", "Long bow", 15.00, 1);

        Assert.assertTrue(product1.compareTo(product3) < 0);
        Assert.assertTrue(product3.compareTo(product1) > 0);
        Assert.assertTrue(product1.compareTo(product2) < 0);
    }
}