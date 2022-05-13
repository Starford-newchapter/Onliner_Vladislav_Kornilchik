import BaseObjects.BaseTest;
import PageObjects.CatalogOnliner.CatalogPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DialogWindowTest extends BaseTest {

    @BeforeTest
    public void getStarted() {
        driver.get(context.getSuite().getParameter("url"));
    }

    @Test
    public void DialogWindowTest(){
        driver.get("https://catalog.onliner.by/");
        get(CatalogPage.class).searchInformation("пылесос").chooseVacuumCleaner().addToCard().checkCountItemsInCard("1");

    }
}
