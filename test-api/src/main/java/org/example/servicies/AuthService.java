package org.example.servicies;

import io.restassured.RestAssured;
import org.example.utils.ConfigReader;

/**
 * Service for user authentication.
 * Retrieves an authentication token using username and password.
 */
public class AuthService {

  private final String baseUrl;
  private final String username;
  private final String password;

  /**
     * Constructor that initializes values from configuration.
     */
  public AuthService() {
    this.baseUrl = ConfigReader.get("base.url");
    this.username = ConfigReader.get("admin.username");
    this.password = ConfigReader.get("admin.password");
  }

  /**
     * Retrieves the authentication token via REST request.
     *
     * @return authentication token string
     */
  public String getAuthToken() {
    return RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body("{\"username\":\""
                        + username
                        + "\", \"password\":\""
                        + password
                        + "\"}")
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
  }
}
