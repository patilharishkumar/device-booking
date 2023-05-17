package com.java.order.phone.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePhone
{
    @JsonProperty("phoneId")
    private String id;

    @JsonProperty("phoneName")
    private String name;
}

