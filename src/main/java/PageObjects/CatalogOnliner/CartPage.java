package PageObjects.CatalogOnliner;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartPage extends BasePage {

    private By titleCart = By.xpath("//*[contains(@class,'title_condensed-additional')]");
    private By nameOfItem = By.xpath("(//*[contains(@href,'vacuumcleaner')])[2]");
    private By removeButton = By.xpath("//*[contains(@class,'cart-form__button_remove')]");
    private By messageAfterRemoveItem = By.xpath("//*[contains(@class,'description_condensed-extra')]");
    private By closeMessageButton = By.xpath("//*[contains(text(),'Закрыть')]");
    private By imageCatEmptyCart = By.xpath("//*[contains(@class,'image_cat')]");
    private By emptyCartText = By.xpath("//*[contains(@class,'cart-message__title_big')]");
    private By countItemsInCart = By.cssSelector(".cart-form__description_extended");


    @Override
    public void verificationPage() {
        Assert.assertEquals(getText(titleCart), "Корзина");
    }

    public CartPage removeItem() {
        actions.moveToElement(findElement(nameOfItem)).perform();
        actions.moveToElement(findElement(removeButton)).click().perform();
        sleep(2);
        return this;
    }

    public CartPage checkMessageAfterRemove() {
        Assert.assertEquals(getText(messageAfterRemoveItem), "Вы удалили Пылесос Xiaomi Mi G9 MJSCXCQ1T");
        return this;
    }

    public CartPage closeMessageAfterRemove() {
        clickButton(closeMessageButton);
        Assert.assertTrue(isElementDisplayed(imageCatEmptyCart));
        Assert.assertEquals(getText(emptyCartText), "Ваша корзина пуста");
        return this;
    }

    public CartPage checkCountItemsInCart() {
        Assert.assertEquals(returnElementArray(countItemsInCart,0),"1");
        return this;

    }


}
