package qa.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import qa.mobile.BaseTest;
import qa.mobile.MenuPage;

public class ProductsPage extends MenuPage {

    @AndroidFindBy (xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup/android.widget.TextView")
    @iOSXCUITFindBy (xpath ="//XCUIElementTypeOther[@name=\"test-Toggle\"]/parent::*[1]/preceding-sibling::*[1]")
    private MobileElement productTitleTxt;

    @AndroidFindBy (xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    @iOSXCUITFindBy (xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
    private MobileElement SLBTitle;

    @AndroidFindBy (xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    @iOSXCUITFindBy (xpath = "(//XCUIElementTypeStaticText[@name=\"test-Price\"])[1]")
    private MobileElement SLBPrice;

    public String getTitle() {
        return getText(productTitleTxt);
    }

    public String getSLBTitle() {
        return getText(SLBTitle);
    }

    public String getSLBPrice() {
        return getText(SLBPrice);
    }

    public ProductDetailsPage pressSLBTitle() {
        click(SLBTitle);
        return new ProductDetailsPage();
    }
}
