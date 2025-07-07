package org.example.mockserver;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import java.net.URI;
import java.net.URISyntaxException;

public class MockServer {
    private static MockServer instance;
    private static String MOCK_SERVER_HOST = "localhost";
    private static int MOCK_SERVER_PORT = 8081;
    private static String MOCK_SERVER_PATH = "";
    private static WireMockServer wireMockServer;

    private MockServer() {}

    public static MockServer getInstance() {
        if (instance == null) {
            instance = new MockServer();
        }
        return instance;
    }

    public void startServer(String apiBaseUrl) throws URISyntaxException {
        if (!apiBaseUrl.isEmpty()) {
            URI uri = new URI(apiBaseUrl);
            MOCK_SERVER_HOST = uri.getHost();
            MOCK_SERVER_PORT = uri.getPort();
            MOCK_SERVER_PATH = uri.getPath();
        }

        WireMockConfiguration config = WireMockConfiguration.wireMockConfig()
                                                            .port(MOCK_SERVER_PORT)
                                                            .extensions(new ResponseTemplateTransformer(false));

        wireMockServer = new WireMockServer(config);
        wireMockServer.start();

        WireMock.configureFor(MOCK_SERVER_HOST, MOCK_SERVER_PORT);

        stubGetBookingById();
        stubCreateBooking();
    }

    private void stubGetBookingById() {
        String jsonResponse = "{\n" +
            "  \"firstname\": \"John\",\n" +
            "  \"lastname\": \"Doe\",\n" +
            "  \"totalprice\": 150,\n" +
            "  \"depositpaid\": true,\n" +
            "  \"bookingdates\": {\n" +
            "    \"checkin\": \"2025-07-01\",\n" +
            "    \"checkout\": \"2025-07-10\"\n" +
            "  },\n" +
            "  \"additionalneeds\": \"Breakfast\"\n" +
            "}";

        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(jsonResponse);

        WireMock.stubFor(
            WireMock.get(MOCK_SERVER_PATH + "/booking/1")
                    .willReturn(mockResponse)
        );
    }

    private void stubCreateBooking() {
        String jsonResponse = "{\n" +
            "  \"bookingid\": 123,\n" +
            "  \"booking\": {\n" +
            "    \"firstname\": \"John\",\n" +
            "    \"lastname\": \"Doe\"\n" +
            "  }\n" +
            "}";

        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(jsonResponse);

        WireMock.stubFor(
            WireMock.post(MOCK_SERVER_PATH + "/booking")
                    .willReturn(mockResponse)
        );
    }

    public void stopServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }
}
