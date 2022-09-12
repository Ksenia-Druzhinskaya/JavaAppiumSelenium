package lib.ui.mobileWeb;

import lib.ui.SavedListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

import static junit.framework.TestCase.assertTrue;

public class MWSavedListPageObject extends SavedListPageObject
{
    static {
        ARTICLE_SUBSTRING_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../a[contains(@class,'watched')]";
    }

    public MWSavedListPageObject(RemoteWebDriver driver){
        super(driver);
    }

    private static String getRemoveButtonByTitle(String articleTitle){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    public void deleteArticle(String articleTitle){
        String removeLocator = getRemoveButtonByTitle(articleTitle);
        this.waitForElementAndClick(
                    removeLocator,
                    "Cannot click button to remove article from the list.",
                    10);
        driver.navigate().refresh();
    }

    public void verifyArticleAdded(String articleTitle){
        String removeLocator = getRemoveButtonByTitle(articleTitle);
        assertTrue("Article '" + articleTitle + "' does not exist in watch list.",
                this.isElementPresent(removeLocator));
    }

}
