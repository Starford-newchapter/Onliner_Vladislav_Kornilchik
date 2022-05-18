package PageObjects.Yandex;

import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.File;

public class YandexImagesPage extends BasePage {
    private By searchField = By.name("text");
    private By fileMenuButton = By.xpath("(//*[contains(@class,'input__cbir-button-icon')])");
    private By chooseFileButton = By.xpath("//*[contains(@class,'Button2_view_action')]");
    private By linkResultOnliner = By.xpath("//a[contains(@href, 'onliner.by')]");
    private String pathToFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "FileUpload" + File.separator;
    private String fileName = "onliner.png";


    public YandexImagesPage openChooseFileMenu() {
        log.debug("Open image upload menu");
        clickButton(fileMenuButton);
        return this;
    }


    public YandexImagesPage uploadImage() {
        openChooseFileMenu();
        uploadFile(this.pathToFile, this.fileName, chooseFileButton);
        return this;

    }

    public YandexImagesPage verifyUpload() {
        log.debug("Image upload successful");
        Assert.assertEquals(getText(linkResultOnliner), "onliner.by");
        return this;
    }

    public YandexImagesPage goToResultLink() {
        log.debug("Open Catalog Onliner Page");
        open("https://" + getText(linkResultOnliner));
        return this;
    }


    @Override
    public void verificationPage() {
        Assert.assertTrue(isElementExist(searchField));
    }


}
