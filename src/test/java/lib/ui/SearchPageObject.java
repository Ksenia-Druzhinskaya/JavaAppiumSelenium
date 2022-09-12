package lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject
{
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_INPUT_ID,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT,
            SEARCH_RESULT_TITLE,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_LIST,
            BACK_BUTTON,
            REMOVE_FROM_SAVED_BUTTON,
            ADD_TO_SAVED_BUTTON;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE methods */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE methods */

    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element.");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element.", 5);
    }

    public void typeSearchLine(String searchLine)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Cannot find and type into search input.", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String searchResultLocator = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultLocator, "Cannot find search result with '" + substring + "'.", 15);
    }

    public void waitAtLeastOneSearchResult()
    {
        this.waitForElementPresent(SEARCH_RESULT, "Search result is empty.", 15);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String searchResultLocator = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultLocator, "Cannot find and click search result with '" + substring + "'.", 15);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Search Cancel button.", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search Cancel button is still present.", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click Search Cancel button.", 5);
    }

    public void assertAllFoundArticlesContainSearchCriteria(String searchLine)
    {
        this.assertListItemsContainSearchCriteria(
                SEARCH_RESULT_TITLE,
                searchLine,
                "Found article does not contain search line '" + searchLine + "'.");
    }

    public WebElement waitForSearchInputPresent(){
        return waitForElementPresent(SEARCH_INPUT_ID, "Cannot find search input.", 5);
    }

    public void assertSeveralArticlesFound(){
        this.assertSeveralListItemsExist(SEARCH_RESULT_LIST, "Only one article is found.");
    }

    public boolean waitForArticleListEmpty(){
        return this.waitForElementNotPresent(SEARCH_RESULT, "Search result is not empty.", 5);
    }

    public void clickBack() {
        this.waitForElementAndClick(BACK_BUTTON, "Cannot find Back button.", 5);
    }

    public void searchAndOpenArticle(String searchLine, String articleTitle){
        initSearchInput();
        typeSearchLine(searchLine);
        clickByArticleWithSubstring(articleTitle);
    }

    public void deleteArticle(String articleTitle){ }

    public void addArticle(String articleTitle){}
}
