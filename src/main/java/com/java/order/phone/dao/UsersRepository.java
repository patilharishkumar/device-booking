package com.java.order.phone.dao;

import com.java.order.phone.dao.entity.Booking;
import com.java.order.phone.dao.entity.Device;
import com.java.order.phone.dao.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * OrderRepository to manage booking and retruns of phone
 */
public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByName(String name);
}
