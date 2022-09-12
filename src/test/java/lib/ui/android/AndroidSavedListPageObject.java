package lib.ui.android;

import lib.ui.SavedListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSavedListPageObject extends SavedListPageObject
{
    static {
        ARTICLE_SUBSTRING_TPL = "xpath://*[@text='{ARTICLE_TITLE}']";
    }

    public AndroidSavedListPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void deleteArticle(String articleTitle){
        String articleResultLocator = getResultArticleElement(articleTitle);
        this.swipeElementToLeft(articleResultLocator, "Cannot find '" + articleTitle + "'.");
    }
}
