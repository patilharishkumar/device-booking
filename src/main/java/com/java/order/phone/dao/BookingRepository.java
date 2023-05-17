package com.java.order.phone.dao;

import com.java.order.phone.dao.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * OrderRepository to manage booking and retruns of phone
 */
public interface BookingRepository extends JpaRepository<Booking, String> {
    Optional<Booking> findById(long id);
    Booking findByDeviceId(long id);
    Booking findByDeviceName(String name);
}
