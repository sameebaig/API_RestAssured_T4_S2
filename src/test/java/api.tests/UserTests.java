package api.tests;

import api.utils.DataProviders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserTests {

    private String apiKey;

    @BeforeClass
    public void setup() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("config/config.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("baseUrl");
        apiKey = prop.getProperty("apikey"); //reqres-free-v1
    }

    @Test(dataProvider = "userIds", dataProviderClass = DataProviders.class)
    public void getUserDetailsAndConditionalDelete(int userId) {

        Response getResponse = RestAssured
                .given()
                .header("x-api-key", apiKey)
                .when()
                .get("/users/" + userId);

        if (getResponse.getStatusCode() == 200) {
            String email = getResponse.jsonPath().getString("data.email");
            System.out.println("Fetched User -> ID: " + userId + ", Email: " + email);

            if (userId == 3) {
                System.out.println("Simulating delete for user ID: " + userId + " (ReqRes API does not support real DELETE)");
            } else {
                System.out.println("No delete action required for user ID: " + userId);
            }

        } else {
            // Fail the test if status is not 200
            Assert.fail("Failed to fetch user details. Expected [200]" 
                        + getResponse.getStatusCode() + "] for user ID: " + userId);
        }
    }
}
