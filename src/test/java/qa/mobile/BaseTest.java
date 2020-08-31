package qa.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import qa.mobile.utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties props;
    protected static HashMap<String, String> strings = new HashMap<String, String>();
    protected static String platform;
    InputStream inputStream;
    InputStream stringsis;
    TestUtils utils;

    public BaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    @Parameters({"platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {
        platform = platformName;
        try {
            props = new Properties();
            String propFileName = "config.properties";
            String xmlFileName = "strings/strings.xml";
            URL url;

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);

            stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            utils = new TestUtils();
            strings = utils.parseStringXML(stringsis);

            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", platformName);
            desiredCapabilities.setCapability("platformVersion", platformVersion);
            desiredCapabilities.setCapability("deviceName", deviceName);

            switch (platformName) {
                case "Android":
                    desiredCapabilities.setCapability("automationName", props.getProperty("androidAutomationName"));
                    desiredCapabilities.setCapability("appPackage", props.getProperty("androidAppPackage"));
                    desiredCapabilities.setCapability("appActivity", props.getProperty("androidAppActivity"));

                    URL androidAppUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
                    desiredCapabilities.setCapability("app", androidAppUrl);

                    url = new URL(props.getProperty("appiumUrl"));

                    driver = new AndroidDriver(url, desiredCapabilities);
                    break;
                case "iOS":
                    desiredCapabilities.setCapability("automationName", props.getProperty("iOSAutomationName"));
                    URL iOSAppUrl = getClass().getClassLoader().getResource(props.getProperty("iOSAppLocation"));
//                    desiredCapabilities.setCapability("app", "iOSAppLocation");
//                    desiredCapabilities.setCapability("automationName", props.getProperty("iOSBundleId"));
                    desiredCapabilities.setCapability("app", "/Users/arsens/Downloads/sample-app-mobile-master/ios/build/SwagLabsMobileApp/Build/Products/Debug-iphonesimulator/SwagLabsMobileApp.app");

                    url = new URL(props.getProperty("appiumUrl"));

                    driver = new IOSDriver(url, desiredCapabilities);
                    break;
                default:
                    throw new Exception("Invalid platform! - " + platformName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if(stringsis != null) {
                stringsis.close();
            }
        }
    }

    public void waitForVisibility(MobileElement e) {
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void clear(MobileElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(MobileElement e) {
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(MobileElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(MobileElement e) {
        switch (platform) {
            case "Android":
                return getAttribute(e, "text");
            case "iOS":
                return getAttribute(e, "label");
        }
        return null;

    }

    public void closeApp() {
        ((InteractsWithApps) driver).closeApp();
    }

    public void launchApp() {
        ((InteractsWithApps) driver).launchApp();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
