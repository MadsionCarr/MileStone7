package app.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({   
	SalableProductTests.class,
    WeaponTest.class,
    ArmorTest.class,
    HealthTest.class,
    InventoryManagerTest.class
    })
public class AllTestsSuite {

}
