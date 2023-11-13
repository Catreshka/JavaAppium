package lib.UI.ios;

import lib.UI.BottomToolbarArticleUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSBottomToolbarArticleUI extends BottomToolbarArticleUI {

    static {
        BUTTON_PAGE_SAVE = "xpath://XCUIElementTypeButton[@name='Save for later']";
        BUTTON_ADD_TO_LIST = "xpath://XCUIElementTypeStaticText[starts-with(@name,'Add')]";
        BUTTON_CREATE_NEW_LIST ="xpath://XCUIElementTypeButton[@name='Create a new list']";
        INPUT_NAME_OF_FOLDER = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        BUTTON_OK = "xpath://XCUIElementTypeStaticText[@name='Create reading list']";
        NAME_OF_EXIST_LIST_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING_NAME_OF_LIST}']";
        BUTTON_VIEW_LIST = "xpath://XCUIElementTypeButton[@name='chevron right']";
    }
    public iOSBottomToolbarArticleUI (RemoteWebDriver driver){
        super(driver);
    }
}
