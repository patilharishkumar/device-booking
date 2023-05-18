package com.java.order.phone.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.order.phone.api.model.*;
import com.java.order.phone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class OrderController {

    @Autowired
    private OrderService phoneOrderService;

    /**
     *  book Phone
     * @param bookingRequest
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping(value = "/book")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<BookingResponse> bookPhone(@RequestBody BookingRequest bookingRequest) throws JsonProcessingException {
            return ResponseEntity.ok(phoneOrderService.bookDevice(bookingRequest));
    }

    /**
     *
     * @param phoneName
     * @return
     */
    @PostMapping(value = "/return/{phoneName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReturnResponse> returnPhone(@PathVariable("phoneName") String phoneName) {
            return ResponseEntity.ok(phoneOrderService.deviceReturn(phoneName));
    }

    /**
     *  GET all Devices
     * @return
     */
    @GetMapping(value = "/devices")
    @ResponseBody
    public ResponseEntity<List<DeviceDetails>> getAllDevices() {
        return ResponseEntity.ok(phoneOrderService.getAllDevices());
    }

    /**
     * bookings
     * @return
     */
    @GetMapping(value = "/bookings")
    @ResponseBody
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(phoneOrderService.getAllBookings());
    }

    /**
     * device
     * @param phoneName
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping(value = "/device/{phoneName}")
    @ResponseBody
    public ResponseEntity<PhoneDetail> getPhoneByName(@PathVariable("phoneName")  String phoneName) throws JsonProcessingException {
        return ResponseEntity.ok(phoneOrderService.getPhoneByName(phoneName));
    }

    /**
     * check Health
     * @return
     */
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("The Phone Order Service is running!");
    }
}
