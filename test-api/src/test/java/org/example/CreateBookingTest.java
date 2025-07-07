package org.example;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.dto.BookingDatesDto;
import org.example.dto.BookingRequestDto;
import org.example.dto.BookingResponseDto;
import org.testng.annotations.Test;

@Epic("Restful-Booker API")
@Feature("Booking Management")
public class CreateBookingTest {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    @Test(description = "Create a new booking")
    @Story("Create Booking")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Olena Olkhovska")
    public void testCreateBooking() {
        // Arrange: create the DTO request
        BookingRequestDto bookingRequest = BookingRequestDto.builder()
                                                            .firstname("Olena")
                                                            .lastname("Olkhovska")
                                                            .totalprice(150)
                                                            .depositpaid(true)
                                                            .bookingdates(BookingDatesDto.builder()
                                                                                         .checkin("2025-07-05")
                                                                                         .checkout("2025-07-10")
                                                                                         .build())
                                                            .additionalneeds("Breakfast")
                                                            .build();

        // Use Allure steps to better structure the test and logging
        Allure.step("Send POST request to create a booking", () -> {
            Response response = RestAssured.given()
                                           .baseUri(BASE_URL)
                                           .contentType(ContentType.JSON)
                                           .body(bookingRequest)
                                           .log().all()  // log request
                                           .when()
                                           .post("/booking")
                                           .then()
                                           .log().all()  // log response
                                           .statusCode(200)
                                           .extract()
                                           .response();

            BookingResponseDto responseDto = response.as(BookingResponseDto.class);

            Allure.step("Verify booking ID is greater than 0", () ->
                assertThat(responseDto.getBookingid()).isGreaterThan(0));

            Allure.step("Verify first name and last name in the response", () -> {
                assertThat(responseDto.getBooking().getFirstname()).isEqualTo("Olena");
                assertThat(responseDto.getBooking().getLastname()).isEqualTo("Olkhovska");
            });
        });
    }
}
