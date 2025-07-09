package org.example.assertion;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import org.example.dto.CreateBookingRequest;

/**
 * Assertions for booking API responses.
 */
public class BookingAssertions {

  /**
     * Asserts that a booking was created successfully and the response data matches expected data.
     *
     * @param response REST response from the booking creation API
     * @param expectedData expected booking data used in the creation request
     */
  public static void assertBookingCreated(Response response, CreateBookingRequest expectedData) {
    assertThat(response.statusCode()).isEqualTo(200);
    assertThat(response.jsonPath().getString("booking.firstname"))
                .isEqualTo(expectedData.getFirstname());
    assertThat(response.jsonPath().getString("booking.lastname"))
                .isEqualTo(expectedData.getLastname());
  }
}
