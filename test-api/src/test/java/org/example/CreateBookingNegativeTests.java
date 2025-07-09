package org.example;

import io.qameta.allure.Allure;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.dto.BookingRequestDto;
import org.example.dto.BookingTestDataFactory;
import org.example.servicies.AuthService;
import org.example.servicies.BookingService;
import org.example.utils.ConfigReader;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Restful-Booker API")
@Feature("Booking Management - Negative Tests")
public class CreateBookingNegativeTests {

    private static final String BASE_URL = ConfigReader.get("base.url");

    private Response lastResponse;

    @BeforeClass
    public void setup() {
        AuthService authService = new AuthService();
        BookingService bookingService = new BookingService();
        String token = authService.getAuthToken();
    }

    @DataProvider(name = "negativeBookings")
    public Object[][] negativeBookings() {
        BookingRequestDto missingFirstName = BookingTestDataFactory.createDefaultBooking();
        missingFirstName.setFirstname(null);

        BookingRequestDto missingLastName = BookingTestDataFactory.createDefaultBooking();
        missingLastName.setLastname(null);

        BookingRequestDto negativePrice = BookingTestDataFactory.createDefaultBooking();
        negativePrice.setTotalprice(-100);

        BookingRequestDto invalidDates = BookingTestDataFactory.createBookingWithCustomDates("2025-09-10", "2025-09-01");

        return new Object[][] {
            { missingFirstName, "Missing first name" },
            { missingLastName, "Missing last name" },
            { negativePrice, "Negative total price" },
            { invalidDates, "Check-out date before check-in date" }
        };
    }

    @Test(dataProvider = "negativeBookings", description = "Create booking with invalid data should fail")
    @Story("Create Booking - Negative")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Olena Olkhovska")
    public void testCreateBookingNegative(BookingRequestDto bookingRequest, String description) {
        Allure.step("Test case: " + description, () -> {
            lastResponse = RestAssured.given()
                                      .baseUri(BASE_URL)
                                      .contentType(ContentType.JSON)
                                      .body(bookingRequest)
                                      .post("/booking")
                                      .then()
                                      .extract()
                                      .response();

            int statusCode = lastResponse.getStatusCode();
            Allure.step("Response status code: " + statusCode);

            assertThat(statusCode).as("Check error status code").isIn(400, 422, 500);
        });
    }

    @AfterMethod
    public void attachResponseOnFailure(ITestResult result) {
        if (lastResponse != null) {
            Allure.addAttachment("API Response", "application/json",
                lastResponse.getBody().asInputStream(), ".json");
        }
    }
}
