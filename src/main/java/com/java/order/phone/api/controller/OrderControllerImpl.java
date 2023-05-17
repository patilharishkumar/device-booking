package com.java.order.phone.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.order.phone.api.model.*;
import com.java.order.phone.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService phoneOrderService;

    @Override
    public ResponseEntity<BookingResponse> bookPhone(@RequestBody BookingRequest bookingRequest) throws JsonProcessingException {
        try {
            return ResponseEntity.ok(phoneOrderService.bookDevice(bookingRequest));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ReturnResponse> returnPhone(String phoneName) {
        try {
            return ResponseEntity.ok(phoneOrderService.deviceReturn(phoneName));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<DeviceDetails>> getAllDevices() {
        return ResponseEntity.ok(phoneOrderService.getAllDevices());
    }

    @Override
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(phoneOrderService.getAllBookings());
    }

    @Override
    public ResponseEntity<PhoneDetail> getPhoneByName(String phoneName) throws JsonProcessingException {
        return ResponseEntity.ok(phoneOrderService.getPhoneByName(phoneName));
    }

    @Override
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("The Phone Order Service is running!");
    }
}
