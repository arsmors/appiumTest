package qa.mobile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Test1 {
    AppiumDriver driver;
    @Test
    public void invalidUserName() {
        MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        usernameTxtFld.click();
        usernameTxtFld.sendKeys("invalidusername");
        passwordTxtFld.click();
        passwordTxtFld.sendKeys("secret_sauce");
        loginBtn.click();

        MobileElement errTxt = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"qa.mobile.test-Error message\"]/android.widget.TextView");

        String actualErrTxt = errTxt.getAttribute("text");
        String expectedErrTxt = "Username and password do not match any user in this service.";

        Assert.assertEquals(actualErrTxt, expectedErrTxt, "creds are not correct");
    }

    @Test
    public void successfulLogin() {
        MobileElement usernameTxtFld = (MobileElement) driver.findElementByAccessibilityId("qa.mobile.test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("qa.mobile.test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("qa.mobile.test-LOGIN");

        usernameTxtFld.sendKeys("standart_user");
        passwordTxtFld.sendKeys("secret_sauce");
        loginBtn.click();

        MobileElement productTitle = (MobileElement) driver.findElementByXPath("//android.widget.ScrollView[@content-desc=\"qa.mobile.test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup/android.widget.TextView");

        String actualProductTitle = productTitle.getAttribute("text");
        String expectedProductTitle = "PRODUCT";

        Assert.assertEquals(actualProductTitle, expectedProductTitle, "creds are not correct");
    }

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("deviceName", "any device name");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        desiredCapabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
        desiredCapabilities.setCapability("app", "/Users/arsens/Downloads/Android.SauceLabs.Mobile.Sample.app.2.3.0.apk");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(url, desiredCapabilities);
        String sessionId = driver.getSessionId().toString();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();

    }

}
