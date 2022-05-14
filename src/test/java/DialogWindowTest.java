import BaseObjects.BaseTest;
import PageObjects.CatalogOnliner.CartPage;
import PageObjects.CatalogOnliner.CatalogPage;
import org.testng.annotations.Test;

public class DialogWindowTest extends BaseTest {


    @Test(priority = 1)
    public void dialogWindowTest() {
        get(CatalogPage.class).open();
        get(CatalogPage.class).verificationPage();
        get(CatalogPage.class).searchInformation("MJSCXCQ1T").checkDialogWindow();

    }

    @Test(priority = 2, dependsOnMethods = "dialogWindowTest")
    public void addItemInCart() {
        get(CatalogPage.class).chooseVacuumCleaner().addToCard().checkCountItemsInCard("1").clickOnShoppingCart();
    }

    @Test(priority = 3, dependsOnMethods = "addItemInCart")
    public void removeItem() {
        get(CartPage.class).verificationPage();
        get(CartPage.class).removeItem().checkMessageAfterRemove().closeMessageAfterRemove();
    }
}
