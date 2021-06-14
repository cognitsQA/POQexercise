package auxiliar.mobile;

import auxiliar.LocalConfiguration;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverCapabilities {

    public static DesiredCapabilities android(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", LocalConfiguration.mobileCapabilities.deviceName);
        desiredCapabilities.setCapability("udid", LocalConfiguration.mobileCapabilities.udId);
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", LocalConfiguration.mobileCapabilities.platformVersion);
        desiredCapabilities.setCapability("appPackage", LocalConfiguration.mobileCapabilities.appPackage);
        desiredCapabilities.setCapability("appActivity", LocalConfiguration.mobileCapabilities.appActivity);
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("noReset", "true");
        return desiredCapabilities;
    }

    public static DesiredCapabilities iOS(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", LocalConfiguration.mobileCapabilities.deviceName);
        desiredCapabilities.setCapability("udid", LocalConfiguration.mobileCapabilities.udId);
        desiredCapabilities.setCapability("platformName", "IOS");
        desiredCapabilities.setCapability("platformVersion", LocalConfiguration.mobileCapabilities.platformVersion);
        desiredCapabilities.setCapability("bundleId", LocalConfiguration.mobileCapabilities.bundleId);
        desiredCapabilities.setCapability("xcodeOrgId)", LocalConfiguration.mobileCapabilities.xcodeOrgId);
        desiredCapabilities.setCapability("xcodeSigningId", "iPhone Developer");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        return desiredCapabilities;
    }

    public static DesiredCapabilities kobiton(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("sessionName", LocalConfiguration.mobileCapabilities.sessionName);
        desiredCapabilities.setCapability("sessionDescription", LocalConfiguration.mobileCapabilities.sessionDescription);
        desiredCapabilities.setCapability("captureScreenshots", LocalConfiguration.mobileCapabilities.captureScreenshots);
        desiredCapabilities.setCapability("app", LocalConfiguration.mobileCapabilities.app);
        desiredCapabilities.setCapability("groupId", LocalConfiguration.mobileCapabilities.groupId);
        desiredCapabilities.setCapability("deviceGroup", LocalConfiguration.mobileCapabilities.deviceGroup);
        desiredCapabilities.setCapability("deviceName", LocalConfiguration.mobileCapabilities.kobitonDeviceName);
        desiredCapabilities.setCapability("platformVersion", LocalConfiguration.mobileCapabilities.kobitonPlatformVersion);
        desiredCapabilities.setCapability("platformName", LocalConfiguration.mobileCapabilities.kobitonPlatformName);
        return desiredCapabilities;
    }
}
