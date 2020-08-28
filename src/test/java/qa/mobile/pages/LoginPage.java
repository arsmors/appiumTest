package qa.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import qa.mobile.BaseTest;

public class LoginPage extends BaseTest {

    @AndroidFindBy (accessibility = "test-Username") private MobileElement usernameTxtFld;
    @AndroidFindBy (accessibility = "test-Password") private MobileElement passwordTxtFld;
    @AndroidFindBy (accessibility = "test-LOGIN") private MobileElement loginBtn;
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private MobileElement errTxt;

    public LoginPage enterUserName(String username) {
        sendKeys(usernameTxtFld, username);
        return this;
    }

    public LoginPage enterPassword(String username) {
        sendKeys(passwordTxtFld, username);
        return this;
    }

    public ProductsPage pressLoginBtn() {
        click(loginBtn);
        return new ProductsPage();
    }

    public String getErrorText() {
        return getAttribute(errTxt, "text");
    }

}
