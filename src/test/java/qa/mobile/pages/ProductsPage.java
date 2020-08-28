package qa.mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import qa.mobile.BaseTest;

public class ProductsPage extends BaseTest {

    @AndroidFindBy (xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup/android.widget.TextView")
    private MobileElement productTitleTxt;

    public String getTitle() {
        return getAttribute(productTitleTxt, "text");
    }
}
