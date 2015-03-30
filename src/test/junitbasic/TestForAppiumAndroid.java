import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sumeet Benawra on 25/03/15.
 */
public class TestForAppiumAndroid {


    private static final String MYO2_DEV_DEBUG_RESOURCE_ID = "uk.co.o2.android.myo2.dev.debug:id/";
    URL serverAddress;
    AndroidDriver driver;

    @Before
    public void setup() throws Exception {

        serverAddress = new URL("http://127.0.0.1:4723/wd/hub");

        DesiredCapabilities capabilities = DesiredCapabilities.android();

        capabilities.setCapability("appium-version", "1.3.6");
//        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android");
//        capabilities.setCapability("platformVersion", "5.0.1");
        capabilities.setCapability("androidPackage", "uk.co.o2.android.myo2.dev.debug");
        capabilities.setCapability("appActivity", "uk.co.o2.android.myo2.activities.SplashScreenActivity");
        capabilities.setCapability("autoLaunch", "true");

        driver = new AndroidDriver(serverAddress, capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//        driver.closeApp();
//        driver.launchApp();
        driver.resetApp();
    }

    @Test
    public void signInSetUpPinEnterPin() throws InterruptedException {

//        Sign in Screen
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "EditUsername\")").sendKeys("447711111121@gmail.com");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "EditPassword\")").sendKeys("password");
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "buttonOKSignIn\")").click();

//      PIN Screen
        assertEquals("Set a 4-digit PIN to keep your details safe:", driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "text1\")").getText());
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "PIN1\")").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "PIN1\")").sendKeys("1234");

//      Home Screen - Broadcast messsage
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "dialogbox_middle\")").click();

        driver.closeApp();
        driver.launchApp();

//      PIN Screen
        assertEquals("Use your PIN to access your account:", driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "text1\")").getText());
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "PIN1\")").sendKeys("4321");
        assertEquals("That's the wrong PIN", driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "dialogbox_title\")").getText());
        assertEquals("That PIN isn't the one you've used before. Have another go or change your PIN.", driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"" + MYO2_DEV_DEBUG_RESOURCE_ID + "dialogbox_message\")").getText());
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Change PIN\")").click();
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Change PIN\")").isDisplayed());
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Changing your PIN means that you'll need to enter your username and password and re-enter a new PIN.\")").isDisplayed());
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"OK\")").click();

//      Sign in Screen
        assertTrue(driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Sign in to review your account\")").isDisplayed());

    }


    @After
    public void tearDown() throws Exception {

        if (driver != null) driver.quit();
    }
}
