import static io.restassured.RestAssured.*;

import RestAssured.RestHelp;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ApiTest extends RestHelp {
    String pathToJson = "src/test/java/ApiRequests/addItem.json";

    @BeforeTest
    public void getStarted() {
        baseURI = "https://catalog.onliner.by/sdapi/";
    }

    @Test(priority = 1)
    public void getVacuumCleaner() {
        Response response = getMethod("catalog.api/search/vacuumcleaner");
        Assert.assertEquals(extractJson(response, "total"), "4742");
        Assert.assertEquals(extractJson(response, "products.full_name[0]"), "Xiaomi Mi G9 MJSCXCQ1T");
    }

    @Test(priority = 2)
    public void getXiaomiCleaner() {
        Response response = getMethod("shop.api/products/mjscxcq1t/positions");
        checkStatusCode(response, 200);
        Assert.assertEquals((extractJson(response, "positions.primary[1].position_price.amount")), "654.36");

    }

    @Test(priority = 3)
    public void checkCleanerInCart() {
        Response response = getMethod("cart.api/v2/detached-cart/b7d34d1e-d036-11ec-aca7-0242f75c1eda?include=position,product&geo_town_id=17030&v=0.9695777704837303");
        checkStatusCode(response, 200);
        Assert.assertEquals(extractJson(response, "position_groups.positions.total_price.amount[0][0]"), "729.00");
        Assert.assertEquals(extractJson(response, "position_groups.positions.product.name[0][0]"), "Mi G9 MJSCXCQ1T");
    }

    @Test(priority = 4)
    public void addItemInCart() {
        Response response = postMethod("cart.api/detached-cart/b7d34d1e-d036-11ec-aca7-0242f75c1eda/add/", pathToJson);
        checkStatusCode(response, 200);
        Assert.assertEquals(extractJson(response, "cart_id"), "b7d34d1e-d036-11ec-aca7-0242f75c1eda");

    }


}
