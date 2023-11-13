package lib.UI.mobile_web;

import lib.UI.BottomToolbarArticleUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWBottomToolbarArticle extends BottomToolbarArticleUI {

    static {
        BUTTON_PAGE_SAVE = "css:li[id='page-actions-watch']";
        BUTTON_LOG_IN = "css:div[class='drawer drawer-container__drawer position-fixed visible']>a";
        INPUT_NAME = "css:div[class='cdx-text-input']>input[id='wpName1']";
        INOUT_PASSWORD = "css:div[class='cdx-text-input']>input[id='wpPassword1']";
        BUTTON_SUBMIT = "css:button[type='submit']";
        BUTTON_WATCHLIST = "css:a[title='Special:Watchlist']";
        UNSAVE_BUTTON = "css:a[aria-controls='mw-watchlink-notification']";
        UPDATE_SAVED = "css://a[class='minerva__tab']";
    }
    public MWBottomToolbarArticle (RemoteWebDriver driver){
        super(driver);
    }
}
