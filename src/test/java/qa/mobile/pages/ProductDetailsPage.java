package qa.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import qa.mobile.MenuPage;

public class ProductDetailsPage extends MenuPage {

    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child:: XCUIElementTypeStaticText[1]")

    private MobileElement SLBTitle;

    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/child:: XCUIElementTypeStaticText[2]")
    private MobileElement SLBText;

    @AndroidFindBy (accessibility = "test-BACK TO PRODUCTS")
    @iOSXCUITFindBy (accessibility = "test-BACK TO PRODUCTS")
    private MobileElement backToProductsBtn;



    public String getSLBTitle() {
        return getText(SLBTitle);
    }

    public String getSLBPText() {
        return getText(SLBText);
    }

    public ProductsPage pressBackToProductsBtn() {
        click(backToProductsBtn);
        return new ProductsPage();
    }
}
