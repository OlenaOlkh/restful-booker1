package org.example.servicies;

import io.restassured.RestAssured;
import org.example.utils.ConfigReader;

public class AuthService {

    private final String baseUrl;
    private final String username;
    private final String password;

    public AuthService() {
        this.baseUrl = ConfigReader.get("base.url");
        this.username = ConfigReader.get("admin.username");
        this.password = ConfigReader.get("admin.password");
    }

    public String getAuthToken() {
        return RestAssured.given()
                          .baseUri(baseUrl)
                          .contentType("application/json")
                          .body("{ \"username\" : \"" + username + "\", \"password\" : \"" + password + "\" }")
                          .when()
                          .post("/auth")
                          .then()
                          .statusCode(200)
                          .extract()
                          .path("token");
    }
}
