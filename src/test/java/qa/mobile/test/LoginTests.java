package qa.mobile.test;

import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.mobile.BaseTest;
import qa.mobile.pages.LoginPage;
import qa.mobile.pages.ProductsPage;
import sun.rmi.runtime.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println("\n" + "starting test: " + m.getName() + "\n");
    }

    @Test
    public void invalidUserName() {
        loginPage.enterUserName("invalidusername");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginBtn();

        String actualErrTxt = loginPage.getErrorText();
        String expectedErrTxt = "Username and password do not match any user in this service.";

        Assert.assertEquals(actualErrTxt, expectedErrTxt, "creds are correct");
    }

    @Test
    public void successfulLogin() {
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        productsPage = loginPage.pressLoginBtn();

        String actualProductTitle = productsPage.getTitle();
        String expectedProductTitle = "PRODUCTS";

        Assert.assertEquals(actualProductTitle, expectedProductTitle, "creds are not correct");
    }
}
