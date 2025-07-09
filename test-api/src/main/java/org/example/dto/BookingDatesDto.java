package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object representing booking dates.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDatesDto {

  /**
     * Check-in date in format YYYY-MM-DD.
     */
  private String checkin;

  /**
     * Check-out date in format YYYY-MM-DD.
     */
  private String checkout;
}
