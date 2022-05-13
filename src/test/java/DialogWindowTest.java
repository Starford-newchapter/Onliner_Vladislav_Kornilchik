import BaseObjects.BaseTest;
import PageObjects.CatalogOnliner.CatalogPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DialogWindowTest extends BaseTest {


    @Test
    public void DialogWindowTest(){
        get(CatalogPage.class).open();
        get(CatalogPage.class).searchInformation("MJSCXCQ1T").chooseVacuumCleaner().addToCard().checkCountItemsInCard("1");

    }
}
