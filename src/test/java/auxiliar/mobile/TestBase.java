package auxiliar.mobile;

import auxiliar.LocalConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase extends GeneralMethods{
    public AppiumDriver<MobileElement> driver;
    public AppiumDriverLocalService server;
    DesiredCapabilities desiredCapabilities;

    @BeforeClass
    public void setUp() {
        URL url;
        switch (LocalConfiguration.mobileCapabilities.environment) {
            case "kobiton" -> {
                desiredCapabilities = DriverCapabilities.kobiton();
                url = createURL();
            }
            case "iOS" -> {
                desiredCapabilities = DriverCapabilities.iOS();
                url = createURL();
            }
            default -> {
                desiredCapabilities = DriverCapabilities.android();
                url = urlServer();
            }
        }
        assert url != null;
        driver = new AppiumDriver<>(url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
    }

    private URL createURL() {
        try {
            return new URL("http://0.0.0.0:4723/wd/hub");
        } catch (Exception Error) {
            Error.printStackTrace();
            return null;
        }
    }

    private URL urlServer(){
        AppiumServiceBuilder serverBuilder = new AppiumServiceBuilder();
        serverBuilder.usingAnyFreePort();
        serverBuilder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        server = AppiumDriverLocalService.buildService(serverBuilder);
        server.start();
        return server.getUrl();
    }

    @AfterClass
    public void closeApp(){
        driver.closeApp();
    }
}
