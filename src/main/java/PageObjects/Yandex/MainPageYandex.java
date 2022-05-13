package PageObjects.Yandex;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MainPageYandex extends BasePage {

    private By yandexLogo = By.className("home-logo__default");
    private By searchField = By.id("text");
    private By imagesIcon = By.xpath("(//*[contains(@class,'new__icon_images')])");
    private String imagesLink = "https://yandex.by/images/?utm_source=main_stripe_big";

    @Override
    public void verificationPage() {
        sleep(2);
        Assert.assertTrue(isElementDisplayed(yandexLogo));
        Assert.assertTrue(isElementExist(searchField));
    }

    public MainPageYandex clickOnYandexImages() {
        clickButton(imagesIcon);
        sleep(4);
        return this;
    }

    public MainPageYandex openYandexImages() {
        open(imagesLink);
        return this;
    }
}
