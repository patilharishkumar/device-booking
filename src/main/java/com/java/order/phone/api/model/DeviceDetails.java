package com.java.order.phone.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
/**
 *  Device class
 */
public class DeviceDetails {
    @JsonProperty("phoneId")
    public long id;

    @JsonProperty("phoneName")
    String name;

    @JsonProperty("count")
    private int count;

    @JsonProperty("available")
    private String available;
}
