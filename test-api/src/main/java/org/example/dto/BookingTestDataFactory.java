package org.example.dto;

import org.example.dto.BookingDatesDto;
import org.example.dto.BookingRequestDto;

public class BookingTestDataFactory {

    public static BookingRequestDto createDefaultBooking() {
        return BookingRequestDto.builder()
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
    }

    public static BookingRequestDto createBookingWithNoBreakfast() {
        return BookingRequestDto.builder()
                                .firstname("Ivan")
                                .lastname("Testov")
                                .totalprice(100)
                                .depositpaid(false)
                                .bookingdates(BookingDatesDto.builder()
                                                             .checkin("2025-08-01")
                                                             .checkout("2025-08-05")
                                                             .build())
                                .additionalneeds(null)
                                .build();
    }

    public static BookingRequestDto createBookingWithCustomDates(String checkin, String checkout) {
        return BookingRequestDto.builder()
                                .firstname("Custom")
                                .lastname("Dates")
                                .totalprice(200)
                                .depositpaid(true)
                                .bookingdates(BookingDatesDto.builder()
                                                             .checkin(checkin)
                                                             .checkout(checkout)
                                                             .build())
                                .additionalneeds("Lunch")
                                .build();
    }
}
