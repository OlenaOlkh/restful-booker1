package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для запроса бронирования.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {
  private String firstname;
  private String lastname;
  private int totalprice;
  private boolean depositpaid;
  private BookingDatesDto bookingdates;
  private String additionalneeds;
}
