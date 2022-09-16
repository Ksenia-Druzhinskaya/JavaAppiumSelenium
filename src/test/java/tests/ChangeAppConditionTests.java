package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import static lib.TestData.ARTICLE_TITLE_JAVA;
import static lib.TestData.SEARCH_LINE_JAVA;
import static org.junit.Assert.assertEquals;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    @DisplayName("Change screen orientation on search results")
    @Description("Find article, open it, change orientation and verify title")
    @Features({@Feature("Search"), @Feature("Article")})
    @Severity(SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchResults(){
        if(Platform.getInstance().isMW()){
            return;
        }

        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.searchAndOpenArticle(SEARCH_LINE_JAVA, ARTICLE_TITLE_JAVA);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String titleBeforeRotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String titleAfterRotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after landscape screen rotation.",
                titleBeforeRotation,
                titleAfterRotation);

        this.rotateScreenPortrait();
        String titleAfterSecondRotation = articlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after portrait screen rotation.",
                titleBeforeRotation,
                titleAfterSecondRotation);
    }

    @Test
    @DisplayName("Check search article in background")
    @Description("Find article, background application and verify search result")
    @Features({@Feature("Search"), @Feature("Article")})
    @Severity(SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground(){
        if(Platform.getInstance().isMW()){
            return;
        }

        skipStartPage();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(SEARCH_LINE_JAVA);
        searchPageObject.waitForSearchResult(ARTICLE_TITLE_JAVA);

        this.backgroundApp(2);
        searchPageObject.waitForSearchResult(ARTICLE_TITLE_JAVA);
    }
}
