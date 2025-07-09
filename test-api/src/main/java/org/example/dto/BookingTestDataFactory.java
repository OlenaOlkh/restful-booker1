package org.example.dto;

/**
 * Factory class to generate test data for booking requests.
 */
public class BookingTestDataFactory {

  /**
     * Creates a default booking with breakfast.
     *
     * @return a BookingRequestDto with predefined values
     */
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

  /**
     * Creates a booking without breakfast.
     *
     * @return a BookingRequestDto without additional needs
     */
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

  /**
     * Creates a booking with custom check-in and check-out dates.
     *
     * @param checkin  the check-in date
     * @param checkout the check-out date
     * @return a BookingRequestDto with custom dates
     */
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
