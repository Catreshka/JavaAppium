package lib.UI;

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

    public WebElement waitForTitleElementInFolder(String substring)
    {
        String element_result_present_xpath = getTitleElement(substring);
        return this.waitForElementPresent(element_result_present_xpath,"Cannot find " + substring + " article in folder",7);
    }

    public WebElement waitForSubTitleElement(String substring)
    {
        String element_result_present_xpath = getSubTitleElement(substring);
        return this.waitForElementPresent(element_result_present_xpath,"Cannot find " + substring + " article in folder",7);
    }
    public String getArticleTitleInFolderMW()
    {
        return this.waitForElementPresent(TITLE_IN_FOLDER_TPL,"Can",3).getText();
    }
    public String getArticleTitleInFolder(String substring)
    {
        WebElement title_element_in_folder = waitForTitleElementInFolder(substring);
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

    public String getArticleSubTitle(String substring)
    {
        WebElement subtitle_element_in_folder = waitForSubTitleElement(substring);
        String element=null;
        if (Platform.getInstance().isAndroid()||Platform.getInstance().isMW()) {
            element = "text";
        } else if (Platform.getInstance().isiOS()) {
            element = "value";
        }
        return subtitle_element_in_folder.getAttribute(element);
    }

    public WebElement waitForTitleElementInArticle()
    {
        return this.waitForElementPresent(TITLE_IN_ARTICLE,"Cannot find title in article",10);
    }
    public void waitForTitleElementInArticleWithoutTimeout()
    {
        this.assertElementPresent(TITLE_IN_ARTICLE,"Cannot find title in article");
    }
    public String getTitleInArticle()
    {
        WebElement title_element = waitForTitleElementInArticle();
        String element=null;
        if (Platform.getInstance().isMW()) {
            element = title_element.getText();
        } else {
            element = title_element.getAttribute("value");
        }
        return element;
    }
    public String getTitleInArticleMW ()
    {
        return this.waitForElementPresent(TITLE_IN_ARTICLE,"Can2",3).getText();
    }
    public void swipeElementDelete(String substring)
    {
        this.waitForArticleToAppearByTitle(substring);
        String element_for_swipe = getTitleElement(substring);
        this.swipeElementToLeft(element_for_swipe,"Cannot find " + substring + " article in saved folder");
        if (Platform.getInstance().isiOS()) {
            this.waitForElementAndClick(BUTTON_DELETE,"Cannot fine delete button",5);
        }
    }

    public void waitForArticleToAppearByTitle(String substring)
    {
        String article_xpath = getTitleElement(substring);
        this.waitForElementPresent(article_xpath,"Cannot find saved article by title " + substring,15);
    }
}
