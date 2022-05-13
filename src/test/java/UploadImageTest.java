import BaseObjects.BaseTest;
import PageObjects.CatalogOnliner.MainPageOnliner;
import PageObjects.Yandex.MainPageYandex;
import PageObjects.Yandex.YandexImagesPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;

public class UploadImageTest extends BaseTest {
    @BeforeTest
    public void getStarted() {
        driver.get(context.getSuite().getParameter("url"));
    }

    @Test
    public void uploadImageWithYandex() throws InterruptedException, AWTException {
        get(MainPageYandex.class).verificationPage();
        get(MainPageYandex.class).openYandexImages();
        get(YandexImagesPage.class).uploadImage().verifyUpload().goToResultLink();
        get(MainPageOnliner.class).verificationPage();
    }



}
