package qa.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import qa.mobile.BaseTest;

public class SettingsPage extends BaseTest {

    @AndroidFindBy (accessibility = "test-LOGOUT")
    @iOSXCUITFindBy(accessibility = "test-LOGOUT")
    private MobileElement logoutBtn;

    public LoginPage pressLogoutBtn() {
        click(logoutBtn);
        return new LoginPage();
    }
}
