package store;

import org.junit.Assert;
import org.junit.Test;

public class ArmorTest {

    @Test
    public void testArmorConstructorAndGetters() {
        Armor armor = new Armor("Steel Armor", "Heavy armor", 60.00, 2, 25);

        Assert.assertEquals("Steel Armor", armor.getName());
        Assert.assertEquals("Heavy armor", armor.getDescription());
        Assert.assertEquals(60.00, armor.getPrice(), 0.001);
        Assert.assertEquals(2, armor.getQuantity());
        Assert.assertEquals(25, armor.getDefense());
    }

    @Test
    public void testSetDefense() {
        Armor armor = new Armor();

        armor.setDefense(30);

        Assert.assertEquals(30, armor.getDefense());
    }

    @Test
    public void testArmorCopyForPurchase() {
        Armor armor = new Armor("Leather Armor", "Light armor", 20.00, 5, 10);

        SalableProduct copy = armor.copyForPurchase();

        Assert.assertTrue(copy instanceof Armor);

        Armor copiedArmor = (Armor) copy;

        Assert.assertEquals("Leather Armor", copiedArmor.getName());
        Assert.assertEquals("Light armor", copiedArmor.getDescription());
        Assert.assertEquals(20.00, copiedArmor.getPrice(), 0.001);
        Assert.assertEquals(1, copiedArmor.getQuantity());
        Assert.assertEquals(10, copiedArmor.getDefense());
    }
}