package lib.UI.android;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        TITLE_IN_FOLDER_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING_TITLE}']";
        TITLE_IN_ARTICLE = "xpath://*[@resource-id='pcs-edit-section-title-description']/preceding-sibling::android.view.View";
    }
    public AndroidArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
