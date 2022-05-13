package PageObjects.CatalogOnliner;

import Entinty.User;
import PageObjects.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage {
    private By titleLogin = By.xpath("//*[contains(@class,'auth-form__title_condensed-default')]");
    private By inputLogin = By.xpath("//*[contains(@placeholder,'e-mail')]");
    private By inputPassword = By.xpath("//*[contains(@placeholder,'Пароль')]");
    private By loginButton = By.xpath("(//*[@type='submit'])[2]");
    private By registrationLink = By.xpath("(//a[contains(@href,'registration')])");
    private By recoverPasswordLink = By.xpath("//a[contains(@href,'https://profile.onliner.by/recover-password')]");
    private By hintFailAuthorization= By.xpath("//*[contains(@class,'auth-form__description_extended-other')]");
    private By hintWithEmptyLogin=By.xpath("(//*[contains(@class,'auth-form__description_extended-other')])[1]");
    private By hintWithEmptyPassword=By.xpath("(//*[contains(@class,'auth-form__description_extended-other')])[2]");

    public LoginPage clickOnRegistrationLink() {
        clickButton(registrationLink);
        return this;
    }




    public LoginPage clickOnRecoverPasswordLink() {
        clickButton(recoverPasswordLink);
        return this;
    }

    public LoginPage clickOnLoginButton(){
        clickButton(loginButton);
        sleep(1);
        return  this;
    }

    public LoginPage sendLogin(String login){
        findElement(inputLogin).sendKeys(login);
        return  this;
    }

    public LoginPage sendPassword(String password){
        findElement(inputPassword).sendKeys(password);
        return  this;
    }

    public LoginPage authorization(User user){
        sendLogin(user.getLogin()).sendPassword(user.getPassword()).clickOnLoginButton();
        return  this;
    }

    public LoginPage checkHintWithErrorAuthorization(){
        Assert.assertEquals(getText(hintFailAuthorization),"Неверный логин или пароль");
        return  this;
    }

    public LoginPage checkHintWithEmptyFields(){
        checkHintWithEmptyLogin().checkHintWithEmptyPassword();
        return  this;
    }

    public LoginPage checkHintWithEmptyLogin(){
        Assert.assertEquals(getText(hintWithEmptyLogin),"Укажите ник или e-mail");
        return  this;
    }

    public LoginPage checkHintWithEmptyPassword(){
        Assert.assertEquals(getText(hintWithEmptyPassword),"Укажите пароль");
        return  this;
    }



    @Override
   public  void verificationPage() {
        Assert.assertTrue(isElementExist(loginButton));
        Assert.assertEquals(getText(titleLogin), "Вход");


    }
}
