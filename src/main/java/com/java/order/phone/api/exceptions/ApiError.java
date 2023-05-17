package com.java.order.phone.api.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ApiError {
    @JsonProperty("status")
    private int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;

    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
