package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import lib.UI.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Search test")
    @Description("Test is searching by word and checking for the presence of the desired article in the search results")
    @Step("Starting test testSearch")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        String search_expected_text = "Object-oriented programming language";
        SearchPageObject.waitForSearchResult(search_expected_text);
    }
    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Cancel search")
    @Description("Test clicks into the input and cancels the search")
    @Step("Starting test testCancelSearch")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Checking input")
    @Description("Test checks that input has default text")
    @Step("Starting test testSearchInputHasText")
    @Severity(value=SeverityLevel.CRITICAL)
    public void testSearchInputHasText()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String expected_text = "Search Wikipedia";
        SearchPageObject.searchExpectedText(expected_text);
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Clear search")
    @Description("Test checks that more than 1 result is found and clears the search")
    @Step("Starting test testClearSearch")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testClearSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue(
                "We found some results ",
                amount_of_search_results>1
        );
        SearchPageObject.clearSearchPhrase();
        SearchPageObject.searchNothingResult();
    }

    @Test
    @Features(value = {@Feature(value="Search")})
    @DisplayName("Find search result")
    @Description("Test checks that in all results the search contains search word")
    @Step("Starting test testFindSearchResult")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testFindSearchResult()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.findWordInSearchResult(search_line);
    }
    @Test
    @Features(value = {@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Find search result for 2 condition")
    @Description("Test performs a search for 2 conditions - title and description")
    @Step("Starting test testFindSearchResultForTwoCondition")
    @Severity(value=SeverityLevel.BLOCKER)
    public void testFindSearchResultForTwoCondition()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Test";
        SearchPageObject.typeSearchLine(search_line);

        String title = "Test";
        String description = "Topics referred to by the same term";

        SearchPageObject.waitForElementByTitleAndDescription(title,description);
    }
}
