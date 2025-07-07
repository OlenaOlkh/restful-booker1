package org.example.assertion;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import org.example.dto.CreateBookingRequest;

public class BookingAssertions {

    public static void assertBookingCreated(Response response, CreateBookingRequest expectedData) {
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("booking.firstname")).isEqualTo(expectedData.getFirstname());
        assertThat(response.jsonPath().getString("booking.lastname")).isEqualTo(expectedData.getLastname());
        // добавь другие поля при необходимости
    }
}

