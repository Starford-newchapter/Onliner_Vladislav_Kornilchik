import BaseObjects.BaseTest;
import Entinty.User;
import PageObjects.CatalogOnliner.CatalogPage;
import PageObjects.CatalogOnliner.RegisterPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class BoundaryValuesTest extends BaseTest {
    String validEmail = "testqa@gmail.com";
    String invalidEmail = "#@#@#GG@gmail.com";
    String passwordLength65 = "UPPTVmxJoJpjI90jObyLuffMSkreXdDdlstTAy8g7IQJS4F1PNU8fbC71npXoA9xP";
    String passwordLength63 = "K27ULwTeSYAah0kIVsbCVfdyAniYBqvt6A04thAKMLKnYK0iCHi2oJOv3MBrE0E";
    String passwordLength64 = "K27ULwTeSYAah0kIVsbCVfdyAniYBqvt6A04thAKMLKnYK0iCHi2oJOv3MBrE0Es";

    @Test(dataProvider = "invalidData", priority = 2)
    public void invalidRegistration(User user) {
        get(CatalogPage.class).open();
        get(CatalogPage.class).verificationPage();
        get(CatalogPage.class).clickOnLoginButton().clickOnRegistrationLink();
        get(RegisterPage.class).verificationPage();
        get(RegisterPage.class).registration(user).getPasswordError();

    }


    @Test(dataProvider = "validData", priority = 1)
    public void validRegistration(User user) {
        get(CatalogPage.class).open();
        get(CatalogPage.class).verificationPage();
        get(CatalogPage.class).clickOnLoginButton().clickOnRegistrationLink();
        get(RegisterPage.class).verificationPage();
        get(RegisterPage.class).registration(user).isRegistrationSuccess(validEmail);

    }

    @Test(priority = 3)
    public void registrationWithInvalidEmail() {
        get(CatalogPage.class).open();
        get(CatalogPage.class).verificationPage();
        get(CatalogPage.class).clickOnLoginButton().clickOnRegistrationLink();
        get(RegisterPage.class).registration(new User.UserBuilder().withLogin(invalidEmail).withPassword("12345678").build()).getEmailError();

    }


    @DataProvider(name = "invalidData")
    public Object[][] invalidUser() {
        return new Object[][]{
                {new User.UserBuilder().withLogin(validEmail).withPassword("1234567").build()}, // length password=7
                {new User.UserBuilder().withLogin(validEmail)
                        .withPassword(passwordLength65).build()} // length password=65
        };
    }

    @DataProvider(name = "validData")
    public Object[][] validUser() {
        return new Object[][]{
                {new User.UserBuilder().withLogin(validEmail).withPassword("1234567q").build()},// length password=8
                {new User.UserBuilder().withLogin(validEmail).withPassword("1234567qw").build()}, // length password=9
                {new User.UserBuilder().withLogin(validEmail)
                        .withPassword(passwordLength63).build()}, //length password=63
                {new User.UserBuilder().withLogin(validEmail)
                        .withPassword(passwordLength64).build()},  //length password=64

        };
    }
}
