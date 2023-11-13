package lib.UI.mobile_web;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE_IN_FOLDER_TPL = "css:a[class='title']>h3";
        TITLE_IN_ARTICLE = "css:div[class='page-heading']>h1>span";
    }
    public MWArticlePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }
}
