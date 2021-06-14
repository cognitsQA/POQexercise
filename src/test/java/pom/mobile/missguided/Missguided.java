package pom.mobile.missguided;

import auxiliar.mobile.GeneralMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Missguided extends GeneralMethods {
    @AndroidFindBy(xpath = "//*[@resource-id='com.poqstudio.app.platform.missguided:id/navigation_item_two']/*[@resource-id='com.poqstudio.app.platform.missguided:id/icon']")
    @iOSXCUITFindBy(accessibility = "TabBar.Shop")
    private MobileElement menuShop;

    @AndroidFindBy(xpath = "//*[@text='Clearance']")
    @iOSXCUITFindBy(accessibility = "clearance")
    private MobileElement clearanceCategory;

    @AndroidFindBy(xpath = "(//*[@resource-id='com.poqstudio.app.platform.missguided:id/product_activity_list_swipe_refresh_layout']//*[@resource-id='com.poqstudio.app.platform.missguided:id/component_progress_image_view_image_view'])[3]")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell[`name == \"ProductList\"`][3]")
    private MobileElement itemSeven;

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/product_info_section_add_to_bag_button")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"add to bag\"`]")
    private MobileElement addBag;

    @AndroidFindBy(xpath = "//*[@resource-id='com.poqstudio.app.platform.missguided:id/numberpicker_input']/..")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]")
    private MobileElement size;

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/actionbar_bag_count_icon")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='wishlist']/following-sibling::XCUIElementTypeStaticText[1]")
    private MobileElement viewBag;

    @AndroidFindBy(xpath = "//*[@text='Shopping Bag']")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`label == \"pay securely\"`]")
    private MobileElement cargo;

    AppiumDriver<MobileElement> driver;

    public Missguided(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public boolean clickMenuShop(){
        return click(driver, menuShop, 20);
    }
    public boolean clickClearanceCategory(){
        return click(driver, clearanceCategory, 20);
    }
    public boolean clickItemNumber(){
        return click(driver, itemSeven, 10);
    }
    public boolean clickAddBag(){
        return click(driver, addBag, 20);
    }
    public boolean clickSize(){
        return click(driver, size, 20);
    }
    public boolean clickViewBag(){
        return click(driver, viewBag, 20);
    }
    public boolean isPageViewBagLoaded(){
        return isDisplayed(driver, cargo, 20);
    }
}
