package com.epam.rd.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * POJO class {@code Order} represents domain entity from order table
 *
 * @author Salakhov Airat
 */

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "supplier.order")
@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name = "customer")
    private String customer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "amount")
    private long amount;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "update_date")
    private ZonedDateTime updateDate;
}
