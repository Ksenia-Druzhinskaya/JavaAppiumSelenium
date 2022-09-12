package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

import static lib.TestData.ARTICLE_TITLE_JAVA;
import static lib.TestData.SEARCH_LINE_JAVA;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
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
