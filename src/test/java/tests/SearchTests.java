package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static lib.TestData.*;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearchArticle(){
        skipStartPage();
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
        searchPageObject.waitForSearchResult(ARTICLE_TITLE_JAVA);
    }

    @Test
    public void testAllFoundArticlesContainSearchLine(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
        searchPageObject.waitAtLeastOneSearchResult();
        searchPageObject.assertAllFoundArticlesContainSearchCriteria(SEARCH_LINE_JAVA);
    }

    @Test
    public void testVerifyDefaultSearchText(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();

        WebElement searchInput = searchPageObject.waitForSearchInputPresent();
        String actualSearchInputText = searchInput.getText();
        assertEquals("Search input does not have default text.", SEARCH_INPUT_TEXT, actualSearchInputText);
    }

    @Test
    public void testCancelSearch(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");

        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testSearchAndClearSearch(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);

        searchPageObject.waitAtLeastOneSearchResult();
        searchPageObject.assertSeveralArticlesFound();

        WebElement searchInput = searchPageObject.waitForSearchInputPresent();
        searchInput.clear();
        searchPageObject.waitForArticleListEmpty();
    }

    @Test
    public void testClearSearchAndVerifyCloseButtonDissapears(){
        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);

        searchPageObject.waitAtLeastOneSearchResult();
        searchPageObject.assertSeveralArticlesFound();

        WebElement searchInput = searchPageObject.waitForSearchInputPresent();
        searchInput.clear();
        searchPageObject.waitForCancelButtonToDisappear();
    }
}
