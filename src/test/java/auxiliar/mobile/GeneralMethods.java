package auxiliar.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class GeneralMethods {
    public Boolean isAndroid(AppiumDriver<MobileElement> driver) {
        return Objects.equals(Objects.requireNonNull(driver.getPlatformName()).toLowerCase(), "android");
    }

    public WebDriverWait waiter(AppiumDriver<MobileElement> driver, int timeOut) {
        return new WebDriverWait(driver, timeOut);
    }

    public Boolean isDisplayed(AppiumDriver<MobileElement> driver, MobileElement element, int timeOut) {
        try {
            waiter(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }

    public Boolean isClickable(AppiumDriver<MobileElement> driver, MobileElement element, int timeOut) {
        try {
            waiter(driver, timeOut).until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }

    public Boolean isEnabled(AppiumDriver<MobileElement> driver, MobileElement element, int timeOut) {
        try {
            if (!isDisplayed(driver, element, timeOut)) {
                return false;
            }
            return element.isEnabled();
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }

    public Boolean click(AppiumDriver<MobileElement> driver, MobileElement element, int timeOut) {
        try {
            isDisplayed(driver, element, timeOut);
            isClickable(driver, element, timeOut);
            element.click();
            return true;
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }

    public Boolean sendKeys(AppiumDriver<MobileElement> driver, MobileElement element, String text, int timeOut) {
        try {
            isDisplayed(driver, element, timeOut);
            isClickable(driver, element, timeOut);
            element.clear();
            element.sendKeys(text);
            return true;
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }

    public Boolean sendKeysNativeBoard(AppiumDriver<MobileElement> driver, MobileElement element, String keys, int
            timeOut) {
        try {
            isDisplayed(driver, element, timeOut);
            isClickable(driver, element, timeOut);
            element.clear();
            element.click();
            driver.getKeyboard().sendKeys(keys);
            driver.hideKeyboard();
            return true;
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }

    public String getText(AppiumDriver<MobileElement> driver, MobileElement element, int timeOut) {
        try {
            isDisplayed(driver, element, timeOut);
            return element.getText();
        } catch (Exception Error) {
            Error.printStackTrace();
            return "There is an exception Error";
        }
    }

    public String getValue(AppiumDriver<MobileElement> driver, MobileElement element, int timeOut) {
        try {
            if (!isDisplayed(driver, element, timeOut)) {
                return null;
            }
            return element.getAttribute("value");
        } catch (Exception Error) {
            Error.printStackTrace();
            return "There is an exception Error";
        }
    }

    public boolean isChecked(AppiumDriver<MobileElement> driver, MobileElement element, int timeOut) {
        try{
            if (!isDisplayed(driver, element, timeOut)) {
                return false;
            }
            return element.getAttribute("checked").equals("true");
        } catch (Exception Error){
            Error.printStackTrace();
            return false;
        }
    }

    public Boolean clear(AppiumDriver<MobileElement> driver, MobileElement element, int timeOut) {
        try {
            if (!isDisplayed(driver, element, timeOut)) {
                return false;
            }
            element.clear();
            return true;
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }

    public void horizontalSwipe(AppiumDriver<MobileElement> driver, int startX, int endX, int y) {
        new TouchAction(driver)
                .press(PointOption.point(startX, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(endX, y))
                .release()
                .perform();
    }

    public void verticalSwipe(AppiumDriver<MobileElement> driver, int startY, int endY, int x) {
        new TouchAction(driver)
                .press(PointOption.point(x, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(x, endY))
                .release()
                .perform();
    }

    public void waitSeconds(int seconds){
        long miliSeconds = (long)seconds *1000;
        try{
            Thread.sleep(miliSeconds);
        }catch(Exception Error){
            Error.printStackTrace();
        }
    }
}
