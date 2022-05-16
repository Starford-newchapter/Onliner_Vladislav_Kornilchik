package PageObjects.CatalogOnliner;

import Entinty.User;
import PageObjects.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;

public class RegisterPage extends BasePage {
    private By titleRegister = By.xpath("//*[contains(text(),'Регистрация')]");
    private By emailInput = By.xpath("//*[@type='email']");
    private By passwordInput = By.xpath("(//*[@type='password'])[1]");
    private By repeatPasswordInput = By.xpath("(//*[@type='password'])[2]");
    private By registerButton = By.xpath("//*[@type='submit']");
    private By checkbox = By.className("i-checkbox__faux");
    private By errorCheckbox = By.className("growl-content");
    private By errorPassword = By.xpath("//*[contains(text(),'должен')]");
    private By errorEmail = By.xpath("//*[contains(text(),'Некорректный e-mail')]");
    private By confirmEmail = By.xpath("//*[contains(@class,'auth-form__title_big')]");
    private By successRegistration = By.xpath("//*[contains(text(),'отправили')]");


    public RegisterPage isRegistrationSuccess(String email) {
        Assert.assertEquals(getText(confirmEmail), "Подтвердите ваш e-mail");
        Assert.assertEquals(returnElementArray(successRegistration, 3), email);
        log.debug("Registration was successful");
        return this;
    }


    public RegisterPage sendEmail(String email) {
        findElement(emailInput).sendKeys(email);
        log.debug("Send " + email + " in email field");
        return this;
    }

    public RegisterPage sendPassword(String password) {
        findElement(passwordInput).sendKeys(password);
        log.debug("Send " + password + " in password field");
        return this;
    }

    public RegisterPage sendRepeatPassword(String password) {
        findElement(repeatPasswordInput).sendKeys(password);
        log.debug("Send " + password + " in Repeat Password field");
        return this;
    }

    public RegisterPage clickOnCheckBox() {
        clickCheckbox(checkbox);
        return this;
    }

    public RegisterPage clickOnRegisterButton() {
        clickButton(registerButton);
        log.debug("Click to Register button");

        return this;
    }

    public RegisterPage registration(User user) {
        sendEmail(user.getLogin()).sendPassword(user.getPassword()).sendRepeatPassword(user.getPassword()).clickOnCheckBox().clickOnRegisterButton();
        sleep(1);
        return this;
    }

    public RegisterPage getCheckBoxError() {
        Assert.assertTrue(isElementDisplayed(errorCheckbox));
        Assert.assertEquals(getText(errorCheckbox), getProperty("errorCheckBoxText"));
        return this;
    }

    public RegisterPage getPasswordError() {
        Assert.assertEquals(getText(errorPassword), getProperty("textErrorPassword"));
        return this;
    }

    public RegisterPage getEmailError() {
        Assert.assertEquals(getText(errorEmail), "Некорректный e-mail");
        return this;
    }


    @Override
    public void verificationPage() {
        Assert.assertEquals(getText(titleRegister), "Регистрация");


    }
}
