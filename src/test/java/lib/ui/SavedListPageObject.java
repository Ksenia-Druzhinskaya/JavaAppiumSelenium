package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SavedListPageObject extends MainPageObject
{
    protected static String
            ARTICLE_SUBSTRING_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    public SavedListPageObject(RemoteWebDriver driver){
        super(driver);
    }

    /* TEMPLATE methods */
    protected static String getResultArticleElement(String substring) {
        return ARTICLE_SUBSTRING_TPL.replace("{ARTICLE_TITLE}", substring);
    }
    /* TEMPLATE methods */

    public void verifyArticleDeleted(String articleTitle){
        String articleResultLocator = getResultArticleElement(articleTitle);
        this.waitForElementNotPresent(articleResultLocator,  "The article '" + articleTitle + "' was not deleted.", 5);
    }

    public void openArticle(String articleTitle){
        String articleResultLocator = getResultArticleElement(articleTitle);
        this.waitForElementAndClick(articleResultLocator,  "Cannot find '" + articleTitle + "'.", 5);
    }

    public void deleteArticle(String articleTitle){}

    public void verifyArticleAdded(String articleTitle){}
}
