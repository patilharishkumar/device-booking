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
public class ReturnResponse {
    @JsonProperty("phoneId")
    private String id;

    @JsonProperty("phoneName")
    private String name;

    @JsonProperty("returnedBy")
    private String returnedBy;

    @JsonProperty("returnedOn")
    private LocalDateTime returnedOn;

    @JsonProperty("isAvailable")
    private String available;

    @JsonProperty("status")
    private String status;
}
