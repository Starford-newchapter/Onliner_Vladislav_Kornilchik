package PageObjects.CatalogOnliner;

import PageObjects.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;


public class CatalogPage extends BasePage {
    private By vacuumItem = By.xpath("(//*[@class='search__result'])[1]");
    private By loginButton = By.cssSelector("div[class$=text]");
    private By shoppingCartButton = By.xpath("(//*[contains(@class,'auth-bar__item--cart')])");
    private By catalogNavigation = By.xpath("(//*[@class='b-main-navigation__text'])[1]");
    private By onlinerLogo = By.className("onliner_logo");
    private By registrationLink = By.xpath("(//*[contains(@href,'registration')])");
    private By trimmersTab = By.cssSelector("#widget-5-8 [href$='trimmers']");
    private By vacuumCleanerTab = By.cssSelector("#widget-3-8 [href$='vacuumcleaner']");
    private By addToCartButton = By.xpath("//*[contains(text(),'В корзину')]");
    private By productCleaner = By.xpath("//*[@class='schema-product__title']");
    private By sideBarAfterAddToCart = By.xpath("(//*[contains(@class,'sidebar-close')])");
    private By countItemInCard = By.className("auth-bar__counter");
    private By nameOfItem = By.xpath("//*[contains(@class,'js-nav-header')]");
    private By searchField = By.className("fast-search__input");
    private By iframe = By.className("modal-iframe");


    public CatalogPage searchInformation(String text) {
        clickButton(searchField);
        log.debug("Open Search");
        findElement(searchField).sendKeys(text);
        log.debug("Send "+text+" in search");
        sleep(2);
        return this;
    }

    public CatalogPage clickOnRegistrationLink() {
        clickButton(registrationLink);
        log.debug("Click on Registration Link");
        return this;
    }


    public CatalogPage openRegisterPage() {
        open(getProperty("urlRegistration"));
        log.debug("Open Registration Page");
        return this;
    }

    public CatalogPage openRecoverPasswordPage() {
        open(getProperty("urlRecoverPassword"));
        log.debug("Open Password Recovery Page");
        return this;
    }


    public CatalogPage clickOnLoginButton() {
        clickButton(loginButton);
        log.debug("Click on Login Button");
        return this;
    }

    public CatalogPage clickOnShoppingCart() {
        clickButton(shoppingCartButton);
        log.debug("Click on Cart Button");
        sleep(2);
        return this;
    }

    public CatalogPage goToVacuumCleanerItems() {
        clickTab(vacuumCleanerTab);
        sleep(2);
        return this;
    }

    public CatalogPage chooseVacuumCleaner() {
        driver.switchTo().frame(findElement(iframe));
        log.debug("Switch to iframe");
        clickButton(vacuumItem);
        log.debug("Click on Item");
        sleep(1);
        Assert.assertEquals(getText(nameOfItem), "Пылесос Xiaomi Mi G9 MJSCXCQ1T");
        return this;
    }

    public CatalogPage checkDialogWindow() {
        Assert.assertTrue(isElementExist(iframe));
        log.debug("Dialog Window  displayed");
        return this;
    }

    public CatalogPage addToCard() {
        clickButton(addToCartButton);
        log.debug("Add Item in Cart");
        sleep(2);
        closeSideBar();
        return this;
    }

    public CatalogPage closeSideBar() {
        clickButton(sideBarAfterAddToCart);
        log.debug("Close SideBar");
        return this;
    }

    public CatalogPage checkCountItemsInCard(String count) {
        Assert.assertEquals(getText(countItemInCard), count);
        log.debug("Item was successful added");
        return this;
    }


    @Override
    public void verificationPage() {
        Assert.assertTrue(isElementDisplayed(onlinerLogo));
        Assert.assertEquals(getText(catalogNavigation), "Каталог");
    }
}
