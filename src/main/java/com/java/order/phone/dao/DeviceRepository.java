package com.java.order.phone.dao;

import com.java.order.phone.dao.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderRepository to manage booking and retruns of phone
 */
public interface DeviceRepository extends JpaRepository<Device, String> {
    Device findByName(String phoneName);
}
