package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBookingRequest {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    @Data
    @Builder
    public static class BookingDates {
        private String checkin;
        private String checkout;
    }
}
