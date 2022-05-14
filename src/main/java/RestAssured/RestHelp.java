package RestAssured;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public abstract class RestHelp {


    protected String getRequestAddItem(String pathToFile) {
        try {
            return new String(Files.readAllBytes(Paths.get(pathToFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    protected ValidatableResponse checkStatusCode(Response response, int status) {
        return (response.then().assertThat().statusCode(status));
    }

    protected String extractJson(Response response, String name) {

        return (response.then().extract().response().jsonPath().get(name).toString());
    }

    protected Response getMethod(String url) {
        return given().when().get(url);
    }

    protected Response postMethod(String url, String pathToFile) {
        return (given().when().header("Content-Type", "application/json").and().body(getRequestAddItem(pathToFile)).post(url));
    }

}



