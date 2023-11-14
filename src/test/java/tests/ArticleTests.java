package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.*;
import lib.UI.factory.ArticlePageObjectFactory;
import lib.UI.factory.BottomToolbarArticleUIFactory;
import lib.UI.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Working with list of favorites")
    @Description("Test is adding 2 articles in favorite list, then deleting 1 selected article and checking that the correct article was deleted ")
    @Step("Starting test testSwipeArticle")
    @Severity(value=SeverityLevel.CRITICAL)
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String searchWord = "Appium";
        String nameArticleOne = "Appium";
        String nameArticleTwo = "Appius Claudius Caecus";
        String subArticleTwo ="Roman statesman";

        SearchPageObject.typeSearchLine(searchWord);
        SearchPageObject.clickByArticleWithSubstring(nameArticleOne);

        String name_folder = "My first folder";

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        BottomToolbarArticleUI BottomToolbarArticleUI = BottomToolbarArticleUIFactory.get(driver);

        BottomToolbarArticleUI.addToList();

        if (Platform.getInstance().isMW()) {
            String name = "Catreshka";
            String password = "wikiWiki2023!";
            BottomToolbarArticleUI.logIn(name,password);

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(searchWord);
            SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);
            BottomToolbarArticleUI.addToList();
            BottomToolbarArticleUI.watchList();
            BottomToolbarArticleUI.deleteArticle();
            BottomToolbarArticleUI.watchList();
            SearchPageObject.waitForSearchNoResult(nameArticleTwo);

        } else {

            BottomToolbarArticleUI.createMyList(name_folder);

            SearchPageObject.clickCancelSearch();
            SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);

            BottomToolbarArticleUI.addToList();
            BottomToolbarArticleUI.addToExistList(name_folder);
            BottomToolbarArticleUI.viewExistListAfterSave();

            ArticlePageObject.swipeElementDelete(nameArticleTwo);
            SearchPageObject.waitForSearchNoResult(nameArticleTwo);
        }

        if (Platform.getInstance().isAndroid()) {
            String article_title_in_folder = ArticlePageObject.getArticleTitleInFolder(nameArticleTwo);
            SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);
            String article_title_in_article = ArticlePageObject.getTitleInArticle();
            Assert.assertEquals(
                    "We see unexpected title",
                    article_title_in_article,
                    article_title_in_folder
            );
        } else if (Platform.getInstance().isiOS()) {
            String article_subtitle_in_folder = ArticlePageObject.getArticleSubTitle(subArticleTwo);
            SearchPageObject.clickByArticleWithSubstring(nameArticleTwo);
            String article_subtitle_in_article = ArticlePageObject.getArticleSubTitle(subArticleTwo);

            Assert.assertEquals(
                    "We see unexpected subtitle",
                    article_subtitle_in_folder,
                    article_subtitle_in_article
            );
        } else {
            String article_title_in_folder = ArticlePageObject.getArticleTitleInFolderMW();
            SearchPageObject.clickBySavedArticleWithSubstring(nameArticleTwo);
            String article_title_in_article = ArticlePageObject.getTitleInArticleMW();
            Assert.assertEquals(
                    "We see unexpected title",
                    article_title_in_article,
                    article_title_in_folder
            );
        }
    }

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Finding articles with title")
    @Description("Test is searching articles for search word, finding in search result article with the defined header")
    @Step("Starting test testFindTitle")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testFindTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);

        String article_title = "Appium";
        SearchPageObject.clickByArticleWithSubstring(article_title);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElementInArticleWithoutTimeout();
    }
}
