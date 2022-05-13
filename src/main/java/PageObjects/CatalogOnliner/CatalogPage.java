package PageObjects.CatalogOnliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;


public class CatalogPage extends BasePage {
    private  By vacuumItem=By.xpath("(//*[@class='search__result'])[1]");
    private By loginButton = By.cssSelector("div[class$=text]");
    private By shoppingCartButton = By.xpath("(//*[contains(@class,'auth-bar__item--cart')])");
    private By catalogNavigation = By.xpath("(//*[@class='b-main-navigation__text'])[1]");
    private  By onlinerLogo=By.className("onliner_logo");
    private  By registrationLink=By.xpath("(//*[contains(@href,'registration')])");
    private String urlRegistration = "https://profile.onliner.by/registration";
    private  String urlRecoverPassword="https://profile.onliner.by/recover-password";
    private  By trimmersTab=By.cssSelector("#widget-5-8 [href$='trimmers']");
    private  By vacuumCleanerTab=By.cssSelector("#widget-3-8 [href$='vacuumcleaner']");
    private  By addToCartButton=By.xpath("//*[contains(text(),'В корзину')]");
    private  By productCleaner=By.xpath("//*[@class='schema-product__title']");
    private  By sideBarAfterAddToCart=By.xpath("(//*[contains(@class,'sidebar-close')])");
    private  By countItemInCard=By.className("auth-bar__counter");
    private By nameOfItem=By.xpath("//*[contains(@class,'js-nav-header')]");
    private  By  searchField=By.className("fast-search__input");
    private  By iframe=By.className("modal-iframe");




    public CatalogPage searchInformation(String text){
        clickButton(searchField);
        findElement(searchField).sendKeys(text);
        sleep(2);
        driver.switchTo().frame(findElement(iframe));
        return  this;
    }
    public CatalogPage clickOnRegistrationLink(){
        clickButton(registrationLink);
        return  this;
    }


    public CatalogPage openRegisterPage() {
        open(urlRegistration);
        return this;
    }

    public CatalogPage OpenRecoverPasswordPage(){
        open(urlRecoverPassword);
        return  this;
    }


    public CatalogPage clickOnLoginButton() {
        clickButton(loginButton);
        return this;
    }

    public CatalogPage clickOnShoppingCart() {
        clickButton(shoppingCartButton);
        sleep(2);
        return this;
    }

    public CatalogPage goToVacuumCleanerItems(){
        clickTab(vacuumCleanerTab);
        sleep(2);
        return  this;
    }

    public CatalogPage chooseVacuumCleaner(){
        clickButton(vacuumItem);
        sleep(1);
        Assert.assertEquals(getText(nameOfItem),"Пылесос Xiaomi Mi G9 MJSCXCQ1T");
        return  this;
    }

    public CatalogPage addToCard(){
        clickButton(addToCartButton);
        sleep(2);
        closeSideBar();
        return  this;
    }
    public CatalogPage closeSideBar(){
        clickButton(sideBarAfterAddToCart);
        return  this;
    }

    public CatalogPage checkCountItemsInCard(String count){
        Assert.assertEquals(getText(countItemInCard),count);
        return  this;
    }




    @Override
    public void verificationPage() {
        Assert.assertTrue(isElementDisplayed(onlinerLogo));
        Assert.assertEquals(getText(catalogNavigation),"Каталог");
    }
}
