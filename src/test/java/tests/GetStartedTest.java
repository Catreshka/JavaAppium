package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for onboarding")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Onboarding")})
    @DisplayName("Welcome test")
    @Description("Test scrolls through all the onboarding screens")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isAndroid()) {
            return;
        }
        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWayToExploreText();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPreferredLangText();
        welcomePage.clickNextButton();

        welcomePage.waitForLearnMoreAboutDateCollectedText();
        welcomePage.clickGetStartedButton();
    }
}
