package com.java.order.phone.dao.entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private int count;

    @Column
    private Boolean available;

    @Column
    private String technology;

    @Column
    private String network2GBands;

    @Column
    private String network3GBands;

    @Column
    private String network4GBands;
}
