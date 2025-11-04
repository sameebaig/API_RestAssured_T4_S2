package api.endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

    private static final String BASE_URL = "https://reqres.in/api";
    public static Response getUserList() {
        return RestAssured.given()
        		.header("x-api-key", "reqres-free-v1")
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .get("/users?page=2");
    }
    public static Response getUserById(int id) {
        return RestAssured.given()
        		.header("x-api-key", "reqres-free-v1")
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .get("/users/" + id);
    }
    public static Response deleteUserById(int id) {
        return RestAssured.given()
        		.header("x-api-key", "reqres-free-v1")
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/" + id);
    }
}
