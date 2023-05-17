package com.java.order.phone.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
/**
 *  BookingResponse DTO class
 */
public class BookingResponse {
    @JsonProperty("phoneId")
    private String id;

    @JsonProperty("phoneName")
    private String name;

    @JsonProperty("bookedBy")
    private String bookedBy;

    @JsonProperty("bookedOn")
    private LocalDateTime bookedDate;

    @JsonProperty("isAvailable")
    private String available;

    @JsonProperty("status")
    private String status;
}
