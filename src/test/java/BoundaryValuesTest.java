import BaseObjects.BaseTest;
import Entinty.User;
import PageObjects.CatalogOnliner.CatalogPage;
import PageObjects.CatalogOnliner.RegisterPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BoundaryValuesTest extends BaseTest {
    String email = "testqa@gmail.com";

    @BeforeMethod
    public void getStarted() {
        driver.get(context.getSuite().getParameter("url"));
    }

    @Test(dataProvider = "invalidData")
    public void invalidRegistration(User user) {
        get(CatalogPage.class).verificationPage();
        get(CatalogPage.class).clickOnLoginButton().clickOnRegistrationLink();
        get(RegisterPage.class).verificationPage();
        get(RegisterPage.class).registration(user).getPasswordError();

    }


    @Test(dataProvider = "validData")
    public void validRegistration(User user) {
        get(CatalogPage.class).verificationPage();
        get(CatalogPage.class).clickOnLoginButton().clickOnRegistrationLink();
        get(RegisterPage.class).verificationPage();
        get(RegisterPage.class).registration(user).isRegistrationSuccess(email);

    }




    @DataProvider(name = "invalidData")
    public Object[][] validUser() {
        return new Object[][]{
                {new User.UserBuilder().withLogin(email).withPassword("1234567").build()}, // length password=7
                {new User.UserBuilder().withLogin(email)
                        .withPassword("UPPTVmxJoJpjI90jObyLuffMSkreXdDdlstTAy8g7IQJS4F1PNU8fbC71npXoA9xP").build()} // length password=65
        };
    }

    @DataProvider(name = "validData")
    public Object[][] invalidUser() {
        return new Object[][]{
                {new User.UserBuilder().withLogin(email).withPassword("1234567q").build()},// length password=8
                {new User.UserBuilder().withLogin(email).withPassword("1234567qw").build()}, // length password=9
                {new User.UserBuilder().withLogin(email)
                        .withPassword("K27ULwTeSYAah0kIVsbCVfdyAniYBqvt6A04thAKMLKnYK0iCHi2oJOv3MBrE0E").build()}, //length password=63
                {new User.UserBuilder().withLogin(email)
                        .withPassword("K27ULwTeSYAah0kIVsbCVfdyAniYBqvt6A04thAKMLKnYK0iCHi2oJOv3MBrE0Es").build()} //length password=64
        };
    }
}
