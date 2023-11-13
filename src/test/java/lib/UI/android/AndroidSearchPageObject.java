package lib.UI.android;

import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_AND_INPUT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SEARCH_CLEAR_PHRASE = "id:org.wikipedia:id/search_src_text";
        SEARCH_BEFORE_INPUT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_empty_container']//*[@text='Search Wikipedia in more languages']";
        SEARCH_RESULT_BY_SUBSTRING_DESCRIPTION_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING_DESCRIPTION}']";
        SEARCH_RESULT_CLICK_BY_SUBSTRING_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING_TITLE}']";
        SEARCH_AMOUNT_RESULT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        SEARCH_RESULT_LIST = "id:org.wikipedia:id/search_results_list";
        SEARCH_ITEM = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULT_TITLE_AND_DESCRIPTION = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING_DESCRIPTION}']/preceding-sibling::android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][contains(text(),'{SUBSTRING_TITLE}')]";
    }
    public AndroidSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
