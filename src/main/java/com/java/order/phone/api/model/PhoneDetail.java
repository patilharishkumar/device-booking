package com.java.order.phone.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDetail extends BasePhone {

    @JsonProperty("technology")
    private String technology;

    @JsonProperty("network2GBands")
    private String network2GBands;

    @JsonProperty("network3GBands")
    private String network3GBands;

    @JsonProperty("network4GBands")
    private String network4GBands;

    @JsonProperty("bookedBy")
    private String bookedBy = "N/A";

    @JsonProperty("bookedOn")
    private LocalDateTime bookedDate;

    @JsonProperty("isAvailable")
    private String available;
}
