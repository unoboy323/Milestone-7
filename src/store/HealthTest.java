package store;

import org.junit.Assert;
import org.junit.Test;

public class HealthTest {

    @Test
    public void testHealthConstructorAndGetters() {
        Health health = new Health("Health Potion", "Restores health", 10.00, 8, 50);

        Assert.assertEquals("Health Potion", health.getName());
        Assert.assertEquals("Restores health", health.getDescription());
        Assert.assertEquals(10.00, health.getPrice(), 0.001);
        Assert.assertEquals(8, health.getQuantity());
        Assert.assertEquals(50, health.getHealingAmount());
    }

    @Test
    public void testSetHealingAmount() {
        Health health = new Health();

        health.setHealingAmount(75);

        Assert.assertEquals(75, health.getHealingAmount());
    }

    @Test
    public void testHealthCopyForPurchase() {
        Health health = new Health("Mega Potion", "Large heal", 25.00, 3, 100);

        SalableProduct copy = health.copyForPurchase();

        Assert.assertTrue(copy instanceof Health);

        Health copiedHealth = (Health) copy;

        Assert.assertEquals("Mega Potion", copiedHealth.getName());
        Assert.assertEquals("Large heal", copiedHealth.getDescription());
        Assert.assertEquals(25.00, copiedHealth.getPrice(), 0.001);
        Assert.assertEquals(1, copiedHealth.getQuantity());
        Assert.assertEquals(100, copiedHealth.getHealingAmount());
    }
}