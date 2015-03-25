import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Created by ee on 25/03/15.
 */
public class TestForAppiumAndroid {


    URL serverAddress;
    AndroidDriver driver;

    @Before
    public void setup() throws Exception {

        serverAddress = new URL("http://127.0.0.1:4723/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.3.6");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformVersion", "5.0.1");
        capabilities.setCapability("androidPackage", "uk.co.o2.android.myo2.dev.debug");
        capabilities.setCapability("appActivity", "uk.co.o2.android.myo2.activities.SplashScreenActivity");

        driver = new AndroidDriver(serverAddress, capabilities);

        driver.closeApp();
        driver.launchApp();
    }

    @Test
    public void simpleTest() {


//        driver.findElementByAndroidUIAutomator("uk.co.o2.android.myo2.dev.debug:id/PIN1").sendKeys("1");

    }


    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }
}
