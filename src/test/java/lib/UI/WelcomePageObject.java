package lib.UI;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject
{
    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
    NEXT_LINK = "id:Next",
    GET_STARTED_BUTTON ="id:Get started",
    SKIP_ELEMENT = "xpath://*[contains(@name,'Skip')]";
    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Waiting for learn more link")
    public void waitForLearnMoreLink()
    {
        screenshot(this.takeScreenshot("more_link"));
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Cannot find 'Learn more about Wikipedia' link",10);
    }

    @Step("Waiting for new way to explore text")
    public void waitForNewWayToExploreText()
    {
        screenshot(this.takeScreenshot("new_way_to_explore_text"));
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,"Cannot find 'New ways to explore' link",10);
    }

    @Step("Waiting for add or edit preferred lang text")
    public void waitForAddOrEditPreferredLangText()
    {
        screenshot(this.takeScreenshot("add_or_edit_preferred_lang_text"));
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,"Cannot find 'Add or edit preferred languages' link",10);
    }

    @Step("Waiting for learn more about date collected text")
    public void waitForLearnMoreAboutDateCollectedText()
    {
        screenshot(this.takeScreenshot("learn_more_about_date_collected_text"));
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,"Cannot find 'Learn more about data collected' link",10);
    }

    @Step("Click to the next button")
    public void clickNextButton()
    {
        screenshot(this.takeScreenshot("next_button"));
        this.waitForElementAndClick(NEXT_LINK,"Cannot find and click 'Next' link",10);
    }

    @Step("Click to get started button")
    public void clickGetStartedButton()
    {
        screenshot(this.takeScreenshot("get_started_button"));
        this.waitForElementAndClick(GET_STARTED_BUTTON,"Cannot find and click 'Get started' link",10);
    }

    @Step("Click to skip button")
    public void clickSkip()
    {
        screenshot(this.takeScreenshot("skip_button"));
        this.waitForElementAndClick(SKIP_ELEMENT, "Cannot find button Skip",5);
    }
}
