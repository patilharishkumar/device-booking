package com.java.order.phone.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.order.phone.api.model.*;


import java.util.List;

public interface OrderService {
    BookingResponse bookDevice(BookingRequest bookingRequest) throws JsonProcessingException;

    List<BookingResponse> getAllBookings();

    List<DeviceDetails> getAllDevices();

    PhoneDetail getPhoneByName(String phoneName) throws JsonProcessingException;

    ReturnResponse deviceReturn(String phoneName);
}
