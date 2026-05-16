package store;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    SalableProductTest.class,
    WeaponTest.class,
    ArmorTest.class,
    HealthTest.class,
    InventoryManagerTest.class
})
public class AllTests {

}