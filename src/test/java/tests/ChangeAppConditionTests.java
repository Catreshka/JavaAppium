package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import lib.UI.factory.ArticlePageObjectFactory;
import lib.UI.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for orientation")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Orientation"),@Feature(value="Article")})
    @DisplayName("Change orientation on search results")
    @Description("Test is opening article, changing orientation and checking that article title haven't been changed after screen rotation")
    @Step("Starting test testChangeOrientationOnSearchResults")
    @Severity(value=SeverityLevel.CRITICAL)
    public void testChangeOrientationOnSearchResults()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);

        String article_title = "Appium";
        SearchPageObject.clickByArticleWithSubstring(article_title);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String titleBeforeRotation = ArticlePageObject.getTitleInArticle();

        this.rotateScreenLandscape();

        String titleAfterRotation = ArticlePageObject.getTitleInArticle();

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                titleBeforeRotation,
                titleAfterRotation
        );
    }
}
