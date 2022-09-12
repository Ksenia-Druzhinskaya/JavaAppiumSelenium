package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{
    protected static String
                        SAVE_BUTTON,
                        SAVED_LABEL,
                        SAVED_BUTTON,
                        BACK_BUTTON,
                        OPEN_NAVIGATION,
                        MY_LISTS_LINK,
                        LOGIN_LINK;

    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }

    public void clickSave() {
        this.waitForElementAndClick(SAVE_BUTTON, "Cannot find Save button.", 5);
    }

    public void waitSaved() {
        this.waitForElementPresent(SAVED_LABEL, "Article is not saved.", 5);
    }

    public void saveAndWaitSaved(){
        clickSave();
        waitSaved();
    }

    public void clickBack() {
        this.waitForElementAndClick(BACK_BUTTON, "Cannot find Back button.", 5);
    }

    public void clickSaved() {
        this.waitForElementAndClick(SAVED_BUTTON, "Cannot find Saved button.", 5);
    }

    public void openNavigation(){}

    public void clickMyLists(){}

    public void clickLogin(){}
}
