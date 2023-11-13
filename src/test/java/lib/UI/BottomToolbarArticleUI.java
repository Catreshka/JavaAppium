package lib.UI;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

abstract public class BottomToolbarArticleUI extends MainPageObject {

    protected static String
            BUTTON_PAGE_SAVE,
            BUTTON_ADD_TO_LIST,
            INPUT_NAME_OF_FOLDER,
            BUTTON_OK,
            NAME_OF_EXIST_LIST_TPL,
            BUTTON_VIEW_LIST,
            BUTTON_CREATE_NEW_LIST,
            INPUT_NAME,
            INOUT_PASSWORD,
            BUTTON_SUBMIT,
            BUTTON_LOG_IN,
            BURGER_MENU,
            BUTTON_WATCHLIST,
            UNSAVE_BUTTON,
            UPDATE_SAVED;

    public BottomToolbarArticleUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getNameOfList(String substring)
    {
        return NAME_OF_EXIST_LIST_TPL.replace("{SUBSTRING_NAME_OF_LIST}",substring);
    }
    /*TEMPLATES METHODS */
    public void addToList ()
    {
        this.waitForElementAndClick(BUTTON_PAGE_SAVE, "Cannot find Save button", 3);
        if (Platform.getInstance().isiOS()) {
            this.waitForElementAndClick(BUTTON_ADD_TO_LIST, "Cannot find button 'Add to list'", 5);
            this.waitForElementAndClick(BUTTON_ADD_TO_LIST, "Cannot find button 'Add to list'", 5);
        } else if (Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(BUTTON_ADD_TO_LIST, "Cannot find button 'Add to list'", 5);
        }
    }

    public void logIn (String name, String password)
    {
        this.waitForElementAndClick(BUTTON_LOG_IN,"Cannot find 'log in' button",5);
        this.waitForElementAndSendKeys(INPUT_NAME,name,"Cannot find input for name",5);
        this.waitForElementAndSendKeys(INOUT_PASSWORD,password,"Cannot find input for password",5);
        this.waitForElementAndClick(BUTTON_SUBMIT,"Cannot find submit button",5);
    }

    public void watchList ()
    {
        this.waitForElementAndClick(BUTTON_WATCHLIST,"Cannot find watchlist button",5);
    }

    public void deleteArticle ()
    {
        this.waitForElementAndClick(UNSAVE_BUTTON,"Cannot find unsaved button",5);
    }

    public void createMyList(String name_of_folder)
    {
        if (Platform.getInstance().isiOS()) {
            this.waitForElementAndClick(BUTTON_CREATE_NEW_LIST, "Cannot find button 'Create new list'",15);
        }
        this.waitForElementAndSendKeys(INPUT_NAME_OF_FOLDER, name_of_folder, "Cannot find input for write name of this list", 5);
        this.waitForElementAndClick(BUTTON_OK, "Cannot find button 'OK'", 5);

    }
    public void addToExistList(String substring)
    {
        String name_of_saves_list = getNameOfList(substring);
        this.waitForElementAndClick(name_of_saves_list, "Cannot find folder " + substring, 5);
    }

    public void viewExistListAfterSave()
    {
        this.waitForElementAndClick(BUTTON_VIEW_LIST, "Cannot click on View list", 5);
    }
}
