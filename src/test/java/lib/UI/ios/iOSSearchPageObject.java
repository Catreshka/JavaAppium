package lib.UI.ios;

import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_AND_INPUT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeButton[@name='Search']";
        SEARCH_RESULT_LIST = "xpath://XCUIElementTypeOther[2]/XCUIElementTypeCollectionView";
        SEARCH_ITEM = "xpath://XCUIElementTypeOther/following::XCUIElementTypeStaticText[1]";
        SEARCH_RESULT_CLICK_BY_SUBSTRING_TITLE_TPL = "xpath://XCUIElementTypeOther/following::XCUIElementTypeStaticText[1][@value='{SUBSTRING_TITLE}']";
        SEARCH_CLEAR_PHRASE = "xpath://XCUIElementTypeButton[@name='Clear text']";
        SEARCH_RESULT_BY_SUBSTRING_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[contains(name(),'{SUBSTRING_DESCRIPTION}')]";
    }
    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
