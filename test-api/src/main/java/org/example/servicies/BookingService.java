package org.example.servicies;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.dto.CreateBookingRequest;
import org.example.utils.ConfigReader;

public class BookingService {

    private final String baseUrl;

    public BookingService() {
        this.baseUrl = ConfigReader.get("base.url");
    }

    public BookingService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Response getBookingById(int id) {
        return RestAssured.given()
                          .baseUri(baseUrl)
                          .when()
                          .get("/booking/" + id)
                          .then()
                          .extract()
                          .response();
    }

    public Response deleteBooking(int id, String token) {
        return RestAssured.given()
                          .baseUri(baseUrl)
                          .contentType("application/json")
                          .cookie("token", token)
                          .when()
                          .delete("/booking/" + id)
                          .then()
                          .extract()
                          .response();
    }

    public Response createBooking(CreateBookingRequest booking) {
        return RestAssured.given()
                          .baseUri(baseUrl)
                          .contentType("application/json")
                          .body(booking)
                          .when()
                          .post("/booking")
                          .then()
                          .extract()
                          .response();
    }

    public Response updateBooking(int id, String token, CreateBookingRequest updateDto) {
        return RestAssured.given()
                          .baseUri(baseUrl)
                          .contentType("application/json")
                          .cookie("token", token)
                          .body(updateDto)
                          .when()
                          .put("/booking/" + id)
                          .then()
                          .extract()
                          .response();
    }
}
