package test.mobile.stage3;

import auxiliar.mobile.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.mobile.missguided.Missguided;

import static org.testng.Assert.assertTrue;

public class AddItemBag extends TestBase {
    Missguided missguided;

    @BeforeMethod
    public void startPom() {
        missguided = new Missguided(driver);
    }

    @Test(description = "")
    public void testClickInputSearch(){
        missguided.clickMenuShop();
        if (driver.getPlatformName().equals("ios")) {
            verticalSwipe(driver, 400, 150, 300);
        } else {verticalSwipe(driver, 1000, 150, 300);}
        missguided.clickClearanceCategory();
        waitSeconds(2);
        if (driver.getPlatformName().equals("ios")) {
            verticalSwipe(driver, 400, 150, 300);
            verticalSwipe(driver, 500, 150, 300);
        } else {
            verticalSwipe(driver, 1900, 150, 300);
        }
        missguided.clickItemNumber();
        if (driver.getPlatformName().equals("ios")) {
            waitSeconds(2);
            verticalSwipe(driver, 500, 150, 300);
        }
        missguided.clickAddBag();
        missguided.clickSize();
        missguided.clickViewBag();
        assertTrue(missguided.isPageViewBagLoaded());
    }
}
