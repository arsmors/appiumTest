package qa.mobile.test;

import io.appium.java_client.MobileElement;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.mobile.BaseTest;
import qa.mobile.pages.LoginPage;
import qa.mobile.pages.ProductsPage;
//import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    InputStream datais;
    JSONObject loginUsers;

    @BeforeClass
    public void beforeClass() throws IOException {
        try {
            String dataFileName = "data/loginUsers.json";
            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(datais);
            loginUsers = new JSONObject(tokener);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (datais != null) {
                datais.close();
            }
        }
    }



    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println("\n" + "starting test: " + m.getName() + "\n");
    }

    @Test
    public void invalidUserName() {
        loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
        loginPage.pressLoginBtn();

        String actualErrTxt = loginPage.getErrorText();
        String expectedErrTxt = strings.get("err_invalid_username_or_password");

        Assert.assertEquals(actualErrTxt, expectedErrTxt, "creds are correct");
    }

    @Test
    public void successfulLogin() {
        loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        productsPage = loginPage.pressLoginBtn();

        String actualProductTitle = productsPage.getTitle();
        String expectedProductTitle = strings.get("product_title");

        Assert.assertEquals(actualProductTitle, expectedProductTitle, "creds are not correct");
    }
}
