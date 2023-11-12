package tests;

import lib.CoreTestCase;
import lib.UI.SearchPageObject;
import lib.UI.factory.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
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
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchInputHasText()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String expected_text = "Search Wikipedia";
        SearchPageObject.searchExpectedText(expected_text);
    }

    @Test
    public void testClearSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found some results ",
                amount_of_search_results>1
        );
        SearchPageObject.clearSearchPhrase();
        SearchPageObject.searchNothingResult();
    }

    @Test
    public void testFindSearchResult()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();

        String search_line = "java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.findWordInSearchResult(search_line);
    }
    @Test
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
