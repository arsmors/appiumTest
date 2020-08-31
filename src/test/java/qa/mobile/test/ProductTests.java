package qa.mobile.test;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import qa.mobile.BaseTest;
import qa.mobile.pages.LoginPage;
import qa.mobile.pages.ProductDetailsPage;
import qa.mobile.pages.ProductsPage;
import qa.mobile.pages.SettingsPage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

//import sun.rmi.runtime.Log;

public class ProductTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;
    SettingsPage settingsPage;
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
            throw e;
        } finally {
            if (datais != null) {
                datais.close();
            }
        }
        closeApp();
        launchApp();
    }



    @BeforeMethod
    public void beforeMethod(Method m) {
        loginPage = new LoginPage();
        System.out.println("\n" + "starting test: " + m.getName() + "\n");

        productsPage = loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
                loginUsers.getJSONObject("validUser").getString("password"));
    }

    @AfterMethod
    public void afterMethod() {
        settingsPage = productsPage.pressSettingsBtn();
        loginPage = settingsPage.pressLogoutBtn();
    }

    @Test
    public void validateProductOnProductsPage() {
        SoftAssert sa = new SoftAssert();

        String SLBTitle = productsPage.getSLBTitle();
        sa.assertEquals(SLBTitle, strings.get("products_page_slb_title"));

        String SLBPrice = productsPage.getSLBPrice();
        sa.assertEquals(SLBPrice, strings.get("products_page_slb_price"));

        sa.assertAll();
    }

    @Test
    public void validateProductOnProductDetailsPage() {
        SoftAssert sa = new SoftAssert();

        productDetailsPage = productsPage.pressSLBTitle();

        String SLBTitle = productDetailsPage.getSLBTitle();
        sa.assertEquals(SLBTitle, strings.get("products_details_page_slb_title"));

        String SLBPrice = productDetailsPage.getSLBPText();
        sa.assertEquals(SLBPrice, strings.get("products_details_page_slb_text"));

        productsPage = productDetailsPage.pressBackToProductsBtn();

        sa.assertAll();
    }
}
