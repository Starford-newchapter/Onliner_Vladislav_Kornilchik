package PageObjects;

import Configuration.PropertyReader;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static BaseObjects.DriverCreation.getDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected Properties properties;
    protected Logger log = Logger.getLogger(BasePage.class);


    protected BasePage() {
        this.driver = getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.actions = new Actions(this.driver);
        this.properties = PropertyReader.getProperties();
    }

    protected abstract void verificationPage();

    public BasePage open() {
        String url = properties.getProperty("url");
        driver.get(url);
        return this;
    }

    protected BasePage open(String url) {
        driver.get(url);
        return this;
    }

    protected BasePage screenshot(String path) {
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);
        File file = (File) takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(path));
        } catch (IOException e) {
        }
        return this;
    }

    protected Boolean isElementExist(By element) {
        List<WebElement> elementList = findElements(element);
        return elementList.size() > 0;

    }

    protected BasePage uploadFile(String pathToFile, String fileName, By uploadFileButton) {
        StringSelection stringSelection = new StringSelection(pathToFile + fileName);
        Toolkit.getDefaultToolkit().getSystemClipboard()
                .setContents(stringSelection, null);
        clickButton(uploadFileButton);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(300);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(300);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(300);
        sleep(5);
        log.debug("Image " + fileName + " chosen");
        return this;

    }

    protected String getProperty(String property) {
        return properties.getProperty(property);
    }

    protected String returnElementArray(By element, int n) {
        String[] array = getText(element).split(" ");
        return array[n];
    }


    protected WebElement findElement(By element) {
        return driver.findElement(element);
    }


    protected List<WebElement> findElements(By element) {
        return driver.findElements(element);
    }

    private BasePage click(By element) {
        findElement(element).click();
        return this;
    }

    protected BasePage clickButton(By element) {
        click(element);
        return this;
    }

    protected BasePage clickCheckbox(By element) {
        click(element);
        log.debug("Click on checkbox");
        return this;
    }

    protected BasePage clickTab(By element) {
        click(element);
        return this;
    }

    public Boolean isElementDisplayed(By element) {
        return findElement(element).isDisplayed();
    }

    protected String getText(By element) {
        return findElement(element).getText();
    }


    protected void sleep(long seconds) {
        ;
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



