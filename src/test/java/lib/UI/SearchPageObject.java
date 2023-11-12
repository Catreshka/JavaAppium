package lib.UI;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_INIT_AND_INPUT_ELEMENT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_CLEAR_PHRASE,
            SEARCH_BEFORE_INPUT_ELEMENT,
            SEARCH_RESULT_BY_SUBSTRING_DESCRIPTION_TPL,
            SEARCH_RESULT_CLICK_BY_SUBSTRING_TITLE_TPL,
            SEARCH_AMOUNT_RESULT,
            SEARCH_RESULT_LIST,
            SEARCH_ITEM,
            SEARCH_RESULT_TITLE_AND_DESCRIPTION,
            SEARCH_INPUT_ELEMENT,
            SAVED_BY_SUBSTRING_DESCRIPTION_TPL,
            TITLE_ARTICLE;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_DESCRIPTION_TPL.replace("{SUBSTRING_DESCRIPTION}",substring);
    }
    private static String getResultSearchTitleElement(String substring)
    {
        return SEARCH_RESULT_CLICK_BY_SUBSTRING_TITLE_TPL.replace("{SUBSTRING_TITLE}",substring);
    }
    private static String getResultSavedTitleElement(String substring)
    {
        return SAVED_BY_SUBSTRING_DESCRIPTION_TPL.replace("{SUBSTRING_TITLE}",substring);
    }
    private static String getResultSearchTitleAndDescription(String substring_title,String substring_description)
    {
        return SEARCH_RESULT_TITLE_AND_DESCRIPTION.replace("{SUBSTRING_TITLE}",substring_title).replace("{SUBSTRING_DESCRIPTION}",substring_description);
    }
    /*TEMPLATES METHODS */

    public void initSearchInput()
    {
            this.waitForElementPresent(SEARCH_INIT_AND_INPUT_ELEMENT, "Cannot find Search Wikipedia input");
            this.waitForElementAndClick(SEARCH_INIT_AND_INPUT_ELEMENT, "Cannot find search input after clicking search input element",5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find Navigate up to cancel",5);
    }
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button",5);
    }
    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot find and click search cancel button",5);
    }
    public void clearSearchPhrase()
    {
        this.waitForElementAndClear(SEARCH_CLEAR_PHRASE,"Cannot find search field",5);
    }
    public void searchNothingResult()
    {
        this.waitForElementPresent(SEARCH_BEFORE_INPUT_ELEMENT,"There is a search result on the screen");
    }

    public void typeSearchLine(String search_line)
    {
        if (Platform.getInstance().isiOS())
        {
            this.waitForElementAndSendKeys(SEARCH_INIT_AND_INPUT_ELEMENT,search_line,"Cannot find search Java input",5);
        } else if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndSendKeys(SEARCH_INIT_AND_INPUT_ELEMENT,search_line,"Cannot find search Java input",5);
        } else {
            this.waitForElementAndSendKeys(SEARCH_INPUT_ELEMENT,search_line,"Cannot find search Java input",5);
        }
    }

    public void waitForSearchResult(String substring)
    {
        if (Platform.getInstance().isMW()) {
            String saved_result_xpath = getResultSavedTitleElement(substring);
            this.waitForElementPresent(saved_result_xpath,"Cannot find article " + substring,15);
        } else {
            String search_result_xpath = getResultSearchElement(substring);
            this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring " + substring,15);
        }
    }

    public void waitForSearchNoResult(String substring)
    {
        String search_no_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(search_no_result_xpath,"Result is still on screen" + substring,15);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchTitleElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring " + substring,15);
    }

    public void clickBySavedArticleWithSubstring(String substring)
    {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(TITLE_ARTICLE,"Cannot find and click saved result with substring " + substring,15);
        }
    }
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_AMOUNT_RESULT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_AMOUNT_RESULT);
    }
    public void findWordInSearchResult(String search_line)
    {
        this.waitForWordInElement(SEARCH_RESULT_LIST, SEARCH_ITEM, "Cannot find " + search_line + " in each search result", search_line);
    }
    public void searchExpectedText(String expected_text)
    {
        if (Platform.getInstance().isiOS())
        {
            this.assertElementHasText(SEARCH_INIT_AND_INPUT_ELEMENT, expected_text, "Element does not exist", "Element does not equal " + expected_text);
        } if (Platform.getInstance().isAndroid()) {
        this.assertElementHasText(SEARCH_INIT_AND_INPUT_ELEMENT, expected_text, "Element does not exist", "Element does not equal " + expected_text);
        } else {
        this.assertElementHasText(SEARCH_INPUT_ELEMENT, expected_text, "Element does not exist", "Element does not equal " + expected_text);
        }
    }
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_for_two_condition = getResultSearchTitleAndDescription(title, description);
        this.waitForElementPresent(search_result_for_two_condition,"Cannot find element with title " + title + " and description " + description,10);
    }
}
