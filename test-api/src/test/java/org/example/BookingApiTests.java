package org.example;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.example.dto.CreateBookingRequest;
import org.example.servicies.AuthService;
import org.example.servicies.BookingService;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;

@Epic("Restful-Booker API")
@Feature("Booking Management")
public class BookingApiTests {

    private AuthService authService;
    private BookingService bookingService;
    private String token;
    private int createdBookingId = -1;
    private Response lastResponse;

    @BeforeClass
    public void setup() {
        authService = new AuthService();
        bookingService = new BookingService();
        token = authService.getAuthToken();
    }

    @Test(description = "Get booking by valid ID")
    @Story("Get Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test checks that booking with ID 2 can be retrieved successfully and contains firstname field")
    public void testGetBookingById() {
        lastResponse = bookingService.getBookingById(2);
        assertThat(lastResponse.statusCode()).isEqualTo(200);
        assertThat(lastResponse.body().asString()).contains("firstname");
    }

    @Test(description = "Create a new booking")
    @Story("Create Booking")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Olena Olkhovska")
    @Description("Test creates a new booking and verifies that booking ID is returned")
    public void testCreateBooking() {
        CreateBookingRequest request = CreateBookingRequest.builder()
                                                           .firstname("John")
                                                           .lastname("Doe")
                                                           .totalprice(150)
                                                           .depositpaid(true)
                                                           .bookingdates(CreateBookingRequest.BookingDates.builder()
                                                                                                          .checkin("2025-07-01")
                                                                                                          .checkout("2025-07-10")
                                                                                                          .build())
                                                           .additionalneeds("Breakfast")
                                                           .build();

        lastResponse = bookingService.createBooking(request);

        assertThat(lastResponse.statusCode()).isEqualTo(200);
        createdBookingId = lastResponse.jsonPath().getInt("bookingid");
        assertThat(createdBookingId).isGreaterThan(0);
    }

    @Test(description = "Delete booking by ID", dependsOnMethods = "testCreateBooking")
    @Story("Delete Booking")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test deletes a booking by ID and verifies response status")
    public void testDeleteBookingById() {
        assertThat(createdBookingId).isGreaterThan(0);
        lastResponse = bookingService.deleteBooking(createdBookingId, token);
        assertThat(lastResponse.statusCode()).isIn(200, 201, 204);
    }

    @AfterMethod
    public void attachResponseOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && lastResponse != null) {
            Allure.addAttachment("API Response", "application/json",
                new ByteArrayInputStream(lastResponse.getBody().asByteArray()), ".json");
        }
    }
}
