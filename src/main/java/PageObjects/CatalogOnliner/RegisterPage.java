package PageObjects.CatalogOnliner;

import Entinty.User;
import PageObjects.BasePage;
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
    private  By confirmEmail=By.xpath("//*[contains(@class,'auth-form__title_big')]");
    private  By successRegistration=By.xpath("//*[contains(text(),'отправили')]");
    private String errorCheckBoxText = "Для регистрации аккаунта необходимо ваше согласие с Политикой конфиденциальности и Пользовательским соглашением";
    private String textErrorPassword = "Пароль должен быть от 8 до 64 символов";

    public  RegisterPage  isRegistrationSuccess(String email){
        Assert.assertEquals(getText(confirmEmail),"Подтвердите ваш e-mail");
        Assert.assertEquals(returnElementArray(successRegistration,3),email);
        return  this;
    }


    public RegisterPage sendEmail(String email) {
        findElement(emailInput).sendKeys(email);
        return this;
    }

    public RegisterPage sendPassword(String password) {
        findElement(passwordInput).sendKeys(password);
        return this;
    }

    public RegisterPage sendRepeatPassword(String password) {
        findElement(repeatPasswordInput).sendKeys(password);
        return this;
    }

    public RegisterPage clickOnCheckBox() {
        clickCheckbox(checkbox);
        return this;
    }

    public RegisterPage clickOnRegisterButton() {
        clickButton(registerButton);
        return this;
    }

    public RegisterPage registration(User user) {
        sendEmail(user.getLogin()).sendPassword(user.getPassword()).sendRepeatPassword(user.getPassword()).clickOnCheckBox().clickOnRegisterButton();
        sleep(1);
        return this;
    }

    public RegisterPage getCheckBoxError() {
        Assert.assertTrue(isElementDisplayed(errorCheckbox));
        Assert.assertEquals(getText(errorCheckbox), errorCheckBoxText);
        return this;
    }

    public RegisterPage getPasswordError() {
        Assert.assertEquals(getText(errorPassword), textErrorPassword);
        return this;
    }


    @Override
    public void verificationPage() {
        Assert.assertEquals(getText(titleRegister), "Регистрация");


    }
}
