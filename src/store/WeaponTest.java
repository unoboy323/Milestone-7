package store;

import org.junit.Assert;
import org.junit.Test;

public class WeaponTest {

    @Test
    public void testWeaponConstructorAndGetters() {
        Weapon weapon = new Weapon("Sword", "Sharp blade", 30.00, 4, 12);

        Assert.assertEquals("Sword", weapon.getName());
        Assert.assertEquals("Sharp blade", weapon.getDescription());
        Assert.assertEquals(30.00, weapon.getPrice(), 0.001);
        Assert.assertEquals(4, weapon.getQuantity());
        Assert.assertEquals(12, weapon.getDamage());
    }

    @Test
    public void testSetDamage() {
        Weapon weapon = new Weapon();

        weapon.setDamage(20);

        Assert.assertEquals(20, weapon.getDamage());
    }

    @Test
    public void testWeaponCopyForPurchase() {
        Weapon weapon = new Weapon("Axe", "Heavy axe", 45.00, 6, 18);

        SalableProduct copy = weapon.copyForPurchase();

        Assert.assertTrue(copy instanceof Weapon);

        Weapon copiedWeapon = (Weapon) copy;

        Assert.assertEquals("Axe", copiedWeapon.getName());
        Assert.assertEquals("Heavy axe", copiedWeapon.getDescription());
        Assert.assertEquals(45.00, copiedWeapon.getPrice(), 0.001);
        Assert.assertEquals(1, copiedWeapon.getQuantity());
        Assert.assertEquals(18, copiedWeapon.getDamage());
    }
}