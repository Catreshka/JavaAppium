package lib.UI;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE_IN_FOLDER_TPL,
            TITLE_IN_ARTICLE,
            BUTTON_DELETE,
            SUBTITLE_TPL;
    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getTitleElement(String substring)
    {
        return TITLE_IN_FOLDER_TPL.replace("{SUBSTRING_TITLE}",substring);
    }
    private static String getSubTitleElement(String substring)
    {
        return SUBTITLE_TPL.replace("{SUBSTRING_SUBTITLE}",substring);
    }
    /*TEMPLATES METHODS */

    @Step("Waiting for title '{substring}' in the folder")
    public WebElement waitForTitleElementInFolder(String substring)
    {
        String element_result_present_xpath = getTitleElement(substring);
        return this.waitForElementPresent(element_result_present_xpath,"Cannot find " + substring + " article in folder",7);
    }

    @Step("Waiting for subtitle '{substring}' on the article page")
    public WebElement waitForSubTitleElement(String substring)
    {
        String element_result_present_xpath = getSubTitleElement(substring);
        return this.waitForElementPresent(element_result_present_xpath,"Cannot find " + substring + " article in folder",7);
    }
    @Step("Getting for title on the article in folder (for mobile web)")
    public String getArticleTitleInFolderMW()
    {
        screenshot(this.takeScreenshot("article_title_in_folder"));
        return this.waitForElementPresent(TITLE_IN_FOLDER_TPL,"Can",3).getText();
    }
    @Step("Getting for title '{substring}' on the article in folder (for iOS/Android)")
    public String getArticleTitleInFolder(String substring)
    {
        WebElement title_element_in_folder = waitForTitleElementInFolder(substring);

        screenshot(this.takeScreenshot("article_title_in_folder"));

        String element=null;
        if (Platform.getInstance().isiOS()) {
            element = title_element_in_folder.getAttribute("value");
        } else if (Platform.getInstance().isAndroid()) {
            element = title_element_in_folder.getAttribute("text");
        } else {
            element = title_element_in_folder.getText();
        }
        return element;
    }

    @Step("Getting for subtitle '{substring}' on the article")
    public String getArticleSubTitle(String substring)
    {
        WebElement subtitle_element_in_folder = waitForSubTitleElement(substring);
        String element=null;
        if (Platform.getInstance().isAndroid()||Platform.getInstance().isMW()) {
            element = "text";
        } else if (Platform.getInstance().isiOS()) {
            element = "value";
        }
        screenshot(this.takeScreenshot("article_subtitle"));
        return subtitle_element_in_folder.getAttribute(element);
    }

    @Step("Waiting for title in the article")
    public WebElement waitForTitleElementInArticle()
    {
        return this.waitForElementPresent(TITLE_IN_ARTICLE,"Cannot find title in article",10);
    }
    @Step("Waiting for title in the article without waiting for the title to appear")
    public void waitForTitleElementInArticleWithoutTimeout()
    {
        this.assertElementPresent(TITLE_IN_ARTICLE,"Cannot find title in article");
    }
    @Step("Getting for title on the article (for iOS/Android)")
    public String getTitleInArticle()
    {
        WebElement title_element = waitForTitleElementInArticle();
        String element=null;
        if (Platform.getInstance().isMW()) {
            element = title_element.getText();
        } else {
            element = title_element.getAttribute("value");
        }
        screenshot(this.takeScreenshot("title_in_article"));
        return element;
    }
    @Step("Getting for title on the article (for mobile web)")
    public String getTitleInArticleMW ()
    {
        screenshot(this.takeScreenshot("title_in_article"));
        return this.waitForElementPresent(TITLE_IN_ARTICLE,"Can2",3).getText();
    }
    @Step("Swiping article '{substring}' for delete")
    public void swipeElementDelete(String substring)
    {
        this.waitForArticleToAppearByTitle(substring);
        String element_for_swipe = getTitleElement(substring);
        this.swipeElementToLeft(element_for_swipe,"Cannot find " + substring + " article in saved folder");
        if (Platform.getInstance().isiOS()) {
            this.waitForElementAndClick(BUTTON_DELETE,"Cannot fine delete button",5);
        }
    }

    @Step("Waiting for article by '{substring}'")
    public void waitForArticleToAppearByTitle(String substring)
    {
        String article_xpath = getTitleElement(substring);
        this.waitForElementPresent(article_xpath,"Cannot find saved article by title " + substring,15);
    }
}
