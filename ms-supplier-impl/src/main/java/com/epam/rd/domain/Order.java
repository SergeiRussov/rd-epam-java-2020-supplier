package com.epam.rd.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * POJO class {@code Order} represents domain entity from order table
 *
 * @author Salakhov Airat
 */

@Data
@Entity
@Table(name = "supplier.order")
@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(name = "customer")
    private String customer;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private long amount;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "update_date")
    private ZonedDateTime updateDate;

    public Order() {
    }

    public Order(String customer, String status, long amount, ZonedDateTime creationDate) {
        this.customer = customer;
        this.status = status;
        this.amount = amount;
        this.creationDate = creationDate;
    }
}
