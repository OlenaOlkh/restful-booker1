package org.example;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.dto.BookingRequestDto;
import org.example.dto.BookingResponseDto;
import org.example.dto.BookingTestDataFactory;
import org.example.utils.ConfigReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})
@Epic("Restful-Booker API")
@Feature("Booking Management")
public class CreateBookingTestDataFactory {

    private static final String BASE_URL = ConfigReader.get("base.url");

    @DataProvider(name = "bookingDataProvider")
    public Object[][] bookingDataProvider() {
        return new Object[][] {
            { BookingTestDataFactory.createDefaultBooking(), "Default booking" },
            { BookingTestDataFactory.createBookingWithNoBreakfast(), "Booking without breakfast" },
            { BookingTestDataFactory.createBookingWithCustomDates("2025-09-01", "2025-09-10"), "Booking with custom dates" }
        };
    }

    @Test(dataProvider = "bookingDataProvider", description = "Create booking with different data sets")
    @Story("Create Booking")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Olena Olkhovska")
    public void testCreateBookingParameterized(BookingRequestDto bookingRequest, String description) {
        Allure.step("Test case: " + description, () -> {
            Response response = RestAssured.given()
                                           .baseUri(BASE_URL)
                                           .contentType(ContentType.JSON)
                                           .body(bookingRequest)
                                           .post("/booking")
                                           .then()
                                           .statusCode(200)
                                           .extract()
                                           .response();

            BookingResponseDto responseDto = response.as(BookingResponseDto.class);

            Allure.step("Verify booking ID is greater than 0", () ->
                assertThat(responseDto.getBookingid()).isGreaterThan(0));

            Allure.step("Verify first name and last name in the response", () -> {
                assertThat(responseDto.getBooking().getFirstname()).isEqualTo(bookingRequest.getFirstname());
                assertThat(responseDto.getBooking().getLastname()).isEqualTo(bookingRequest.getLastname());
            });

            if (bookingRequest.getBookingdates() != null) {
                Allure.step("Verify check-in and check-out dates", () -> {
                    assertThat(responseDto.getBooking().getBookingdates().getCheckin())
                        .isEqualTo(bookingRequest.getBookingdates().getCheckin());
                    assertThat(responseDto.getBooking().getBookingdates().getCheckout())
                        .isEqualTo(bookingRequest.getBookingdates().getCheckout());
                });
            }
        });
    }
}
