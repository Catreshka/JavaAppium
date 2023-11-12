package lib.UI.mobile_web;

import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_AND_INPUT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT_ELEMENT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_LIST = "css://div.results";
        SEARCH_ITEM = "css://ul[class='page-list thumbs actionable']>li.page-summary>a>h3>strong";
        SEARCH_RESULT_CLICK_BY_SUBSTRING_TITLE_TPL = "xpath://h3[text()='{SUBSTRING_TITLE}']";
        SEARCH_CLEAR_PHRASE = "css:button.clear";
        SEARCH_RESULT_BY_SUBSTRING_DESCRIPTION_TPL = "xpath://div[@class='wikidata-description'][contains(text(),'{SUBSTRING_DESCRIPTION}')]";
        SAVED_BY_SUBSTRING_DESCRIPTION_TPL = "css:a[class='title']>h3[contains(text(),'{SUBSTRING_DESCRIPTION}')]";
        TITLE_ARTICLE = "css:a[class='title']>h3";
    }
    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
