package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SavedPageObject extends MainPageObject
{
    protected static String SAVED_LABEL_SUBSTRING_TPL;

    public SavedPageObject(RemoteWebDriver driver){
        super(driver);
    }

    /* TEMPLATE methods */
    private static String getResultSavedArticlesElement(Integer articleNumber) {
        return SAVED_LABEL_SUBSTRING_TPL.replace("{NUMBER}", articleNumber.toString());
    }
    /* TEMPLATE methods */

    public void clickSavedArticles(Integer articleNumber) {
        String resultLocator = getResultSavedArticlesElement(articleNumber);
        this.waitForElementAndClick(resultLocator, "Cannot find Saved for articles label.", 5);
    }
}
