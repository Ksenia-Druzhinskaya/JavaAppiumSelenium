package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class StartPageObject extends MainPageObject
{
    protected static String SKIP_BUTTON;

    public StartPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void skipStartPage() {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find Skip button on Start page.", 5);
    }
}
