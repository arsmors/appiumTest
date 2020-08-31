package qa.mobile;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import qa.mobile.pages.SettingsPage;

public class MenuPage extends BaseTest{

    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy (xpath= "//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")
    private MobileElement settingsBtn;

    public SettingsPage pressSettingsBtn() {
        click(settingsBtn);
        return new SettingsPage();
    }
}
