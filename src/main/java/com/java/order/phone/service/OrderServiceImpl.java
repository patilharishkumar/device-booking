package com.java.order.phone.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.java.order.phone.api.model.*;
import com.java.order.phone.common.DeviceConstant;
import com.java.order.phone.dao.BookingRepository;
import com.java.order.phone.dao.DeviceRepository;
import com.java.order.phone.dao.UsersRepository;
import com.java.order.phone.dao.entity.Booking;
import com.java.order.phone.dao.entity.Device;
import com.google.gson.JsonObject;
import com.java.order.phone.dao.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rapidAPI.key}")
    private String API_KEY;

    @Value("${rapidAPI.host}")
    private String API_HOST;

    /**
     * Method to book device in database with correct status.
     *
     * @param bookingRequest
     * @return BookingResponse
     * @throws JsonProcessingException
     */

    @Override
    public BookingResponse bookDevice(BookingRequest bookingRequest) throws JsonProcessingException {
        // find device details  in database by name

        Device device = deviceRepository.findByName(bookingRequest.getName());
        if (device == null)
            throw new RuntimeException(bookingRequest.getName() + " No Device Found!");
        Users user= usersRepository.findByName(bookingRequest.getBookedBy());
        if (user == null)
            throw new RuntimeException(bookingRequest.getBookedBy()+ " No user Found!");

        // Check if phone is available with count
        if (device.getAvailable()) {
            if (device.getCount()!= 0) {
                int count = device.getCount();
                device.setCount(count - 1);
                if (device.getCount() == 0)
                    device.setAvailable(false);
            }

            Booking savedBooking = bookingRepository.save(new Booking(device,user,LocalDateTime.now(),"booked"));
            deviceRepository.save(device);
            // book phone and send response back
            return new BookingResponse(Long.toString(savedBooking.getId()), savedBooking.getDevice().getName(), savedBooking.getUser().getName(), savedBooking.getBookedDate(), savedBooking.getDevice().getAvailable() ? "Yes" : "No",savedBooking.getStatus());
        } else {
            throw new RuntimeException(bookingRequest.getName() + " can't be booked again!");
        }
    }

    /**
     * Get list of all phones in database with current status.
     *
     * @return List<BookingResponse>
     */
    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .parallelStream()
                .map(booking -> new BookingResponse(Long.toString(booking.getId()), booking.getDevice().getName(), booking.getUser().getName(), booking.getBookedDate(), booking.getDevice().getAvailable() ? "Yes" : "No", booking.getStatus()))
                .collect(Collectors.toList());
    }


    /**
     * Get list of all phones in database with current status.
     *
     * @return List<BookingResponse>
     */
    @Override
    public List<DeviceDetails> getAllDevices() {
        return deviceRepository.findAll()
                .parallelStream()
                .map(device -> new DeviceDetails(device.getId(), device.getName(), device.getCount(), device.getAvailable() ? "Yes" : "No"))
                .collect(Collectors.toList());
    }

    /**
     * get Phone specification by ByName from rapid api
     *
     * @param phoneName
     * @return PhoneDetail
     * @throws JsonProcessingException
     */
    @Override
    public PhoneDetail getPhoneByName(String phoneName) throws JsonProcessingException {
        Device device = deviceRepository.findByName(phoneName);
        if(device.getTechnology() == null) {
            device = fetchDataFromRapidApi(device, phoneName);
        }
        PhoneDetail phoneResponse = new PhoneDetail();
        phoneResponse.setTechnology(device.getTechnology());
        phoneResponse.setNetwork2GBands(device.getNetwork2GBands());
        phoneResponse.setNetwork3GBands(device.getNetwork3GBands());
        phoneResponse.setNetwork4GBands(device.getNetwork4GBands());
        phoneResponse.setId(Long.toString(device.getId()));
        phoneResponse.setName(device.getName());
        phoneResponse.setAvailable(device.getAvailable() ? "Yes" : "No");
        phoneResponse.setBookedBy(device.getAvailable() ? "N/A" : "");
        return phoneResponse;
    }

    /**
     *  Device return
     * @param phoneName
     * @return
     */
    @Override
    public ReturnResponse deviceReturn(String phoneName) {
        Device device = deviceRepository.findByName(phoneName);
        Booking booking = bookingRepository.findByDeviceId(device.getId());

        if (null == booking) throw new RuntimeException(phoneName + " was never booked, so it can't be returned!");
        else if (booking.getDevice().getAvailable())
            throw new RuntimeException(phoneName + " was never booked, so it can't be returned!");
        else {
            booking.getDevice().setAvailable(true);
            int currentCount = booking.getDevice().getCount();
            booking.getDevice().setCount(currentCount + 1);
            booking.setStatus("returned");
            bookingRepository.save(booking);
        }
        return new ReturnResponse(Long.toString(booking.getId()), booking.getDevice().getName(), booking.getUser().getName(), booking.getModifyDate(), booking.getDevice().getAvailable() ? "Yes" : "No",booking.getStatus());
    }

    /**
     *  Get Phone data
     * @param phoneName
     * @return
     * @throws JsonProcessingException
     */
    private JsonObject getPhoneData(String phoneName) throws JsonProcessingException {
        String endPoint;
        switch (phoneName) {
            case "Samsung Galaxy S9":
                endPoint = DeviceConstant.SAMSUNG_GALAXY_S9;
                break;
            case "Samsung Galaxy S8":
                endPoint = DeviceConstant.SAMSUNG_GALAXY_S8;
                break;
            case "Samsung Galaxy S8+":
                endPoint = DeviceConstant.SAMSUNG_GALAXY_S8_PLUS;
                break;
            case "Google Nexus 6":
                endPoint = DeviceConstant.MOTOROLA_NEXUS_6;
                break;
            case "Oneplus 9":
                endPoint = DeviceConstant.ONE_PLUS_9;
                break;
            case "Apple iPhone 13":
                endPoint = DeviceConstant.IPHONE_13;
                break;
            case "Apple iPhone 12":
                endPoint = DeviceConstant.IPHONE_12;
                break;
            case "Apple iPhone 11":
                endPoint = DeviceConstant.IPHONE_11;
                break;
            case "iPhone X":
                endPoint = DeviceConstant.IPHONE_X;
                break;
            case "Nokia 3310":
                endPoint = DeviceConstant.NOKIA_3310;
                break;
            default:
                throw new RuntimeException("The Phone doesn't exist!");
        }


        HttpHeaders headers = new HttpHeaders();
        headers.set(DeviceConstant.HEADER_RAPID_API, API_KEY);
        headers.set(DeviceConstant.HEADER_RAPID_API_HOST, API_HOST);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "/" + endPoint, HttpMethod.GET, requestEntity, String.class);
        JsonObject jsonObject = new Gson().fromJson(response.getBody().toString(), JsonObject.class);
        return jsonObject;
    }

    /**
     *  Fetch Data from Rapid API
     * @param device
     * @param phoneName
     * @return
     * @throws JsonProcessingException
     */
    private Device fetchDataFromRapidApi(Device device, String phoneName) throws JsonProcessingException {

        JsonObject data = getPhoneData(phoneName);
        JsonObject gsmNetworkDetails = data.getAsJsonObject(DeviceConstant.NETWORK_DETAILS);
        String network2GBands = null;
        String network3GBands = null;
        String network4GBands = null;
        // network 2G bands
        if (gsmNetworkDetails.has(DeviceConstant.NETWORK_2G_BANDS))
            network2GBands = gsmNetworkDetails.get(DeviceConstant.NETWORK_2G_BANDS).getAsString();

        // network 3G bands
        if (gsmNetworkDetails.has(DeviceConstant.NETWORK_3G_BANDS))
            network3GBands = gsmNetworkDetails.get(DeviceConstant.NETWORK_3G_BANDS).getAsString();

        // network 4G bands
        if (gsmNetworkDetails.has(DeviceConstant.NETWORK_4G_BANDS))
            network4GBands = gsmNetworkDetails.get(DeviceConstant.NETWORK_4G_BANDS).getAsString();

        // Tech details about phone
        String tech = gsmNetworkDetails.get(DeviceConstant.NETWORK_TECHNOLOGY).getAsString();

        device.setNetwork2GBands(network2GBands);
        device.setNetwork3GBands(network3GBands);
        device.setNetwork4GBands(network4GBands);
        device.setTechnology(tech);
        deviceRepository.save(device);
        return  device;
    }
}
