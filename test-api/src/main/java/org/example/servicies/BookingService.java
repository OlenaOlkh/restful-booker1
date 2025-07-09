package org.example.servicies;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.dto.CreateBookingRequest;
import org.example.utils.ConfigReader;

/**
 * Service class for interacting with Booking API.
 */
public class BookingService {

  private final String baseUrl;

  /**
     * Default constructor, reads base URL from config.
     */
  public BookingService() {
    this.baseUrl = ConfigReader.get("base.url");
  }

  /**
     * Constructor with base URL parameter.
     *
     * @param baseUrl base URL of the booking API
     */
  public BookingService(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
     * Get booking by ID.
     *
     * @param id booking ID
     * @return Response from the API
     */
  public Response getBookingById(int id) {
    return RestAssured.given()
                .baseUri(baseUrl)
                .when()
                .get("/booking/" + id)
                .then()
                .extract()
                .response();
  }

  /**
     * Delete booking by ID with authentication token.
     *
     * @param id booking ID
     * @param token authentication token
     * @return Response from the API
     */
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

  /**
     * Create a new booking.
     *
     * @param booking booking details DTO
     * @return Response from the API
     */
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

  /**
     * Update booking by ID with authentication token.
     *
     * @param id booking ID
     * @param token authentication token
     * @param updateDto updated booking details DTO
     * @return Response from the API
     */
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
