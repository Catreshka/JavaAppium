package lib.UI.ios;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE_IN_FOLDER_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING_TITLE}']/..";
        BUTTON_DELETE = "xpath://XCUIElementTypeButton[@name='swipe action delete']";
        SUBTITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING_SUBTITLE}']";
    }
    public iOSArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
