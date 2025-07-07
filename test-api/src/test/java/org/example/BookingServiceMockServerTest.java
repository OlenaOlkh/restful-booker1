package org.example;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.example.servicies.BookingService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class BookingServiceMockServerTest {

    private WireMockServer wireMockServer;
    private BookingService bookingService;

    private static final int PORT = 8089;
    private static final String BASE_URL = "http://localhost:" + PORT;

    @BeforeClass
    public void setUp() {
        wireMockServer = new WireMockServer(PORT);
        wireMockServer.start();
        wireMockServer.resetAll();

        configureFor("localhost", PORT);

        stubFor(get(urlEqualTo("/booking/1"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{ \"firstname\": \"John\", \"lastname\": \"Doe\", \"totalprice\": 150 }")));

        bookingService = new BookingService(BASE_URL);
    }

    @AfterClass
    public void tearDown() {
        if (wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    @Test(description = "Verify getBookingById returns correct data from mock server")

    public void testGetBookingById() {
        var response = bookingService.getBookingById(1);
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("firstname")).isEqualTo("John");
        assertThat(response.jsonPath().getString("lastname")).isEqualTo("Doe");
        assertThat(response.jsonPath().getInt("totalprice")).isEqualTo(150);
    }
}
