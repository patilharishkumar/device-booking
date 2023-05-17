package com.java.order.phone.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.order.phone.api.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/api/v1/")
public interface OrderController {

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    ResponseEntity<BookingResponse> bookPhone(@RequestBody BookingRequest bookingRequest) throws JsonProcessingException;

    @RequestMapping(value = "/return", method = RequestMethod.DELETE)
    ResponseEntity<ReturnResponse> returnPhone(@RequestParam("name") String phoneName) throws JsonProcessingException;

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    ResponseEntity<List<BookingResponse>> getAllBookings();

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    ResponseEntity<List<DeviceDetails>> getAllDevices();

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    ResponseEntity<PhoneDetail> getPhoneByName(@RequestParam("name") String phoneName) throws JsonProcessingException;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    ResponseEntity<String> checkHealth();

}
