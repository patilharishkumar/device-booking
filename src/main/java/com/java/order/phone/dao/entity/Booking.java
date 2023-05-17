package com.java.order.phone.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @CreationTimestamp
    @Column(name="booked_date", updatable = false, nullable = false)
    private LocalDateTime bookedDate;

    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @Column(nullable = true)
    private String status;

    public Booking(Device device, Users user, LocalDateTime bookedDate, String status) {
        this.device = device;
        this.user = user;
        this.bookedDate = bookedDate;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
