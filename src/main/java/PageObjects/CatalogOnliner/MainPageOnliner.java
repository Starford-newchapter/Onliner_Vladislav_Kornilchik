package PageObjects.CatalogOnliner;

import PageObjects.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;


public class MainPageOnliner extends BasePage {
    private By onlinerLogo = By.className("onliner_logo");
    private By catalogOnlinerLink = By.xpath("(//*[@href='https://catalog.onliner.by'])[2]");

    @Override
    public void verificationPage() {
        Assert.assertTrue(isElementDisplayed(onlinerLogo));
        Assert.assertEquals(getText(catalogOnlinerLink), "Каталог");

    }

    public MainPageOnliner goToCatalogPage() {
        log.debug("Open CatalogOnliner Page");
        clickButton(catalogOnlinerLink);
        return this;
    }
}
