package com.epam.rd.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
public class Order {
    @Id
    @GeneratedValue(generator = "uuid")
    UUID id;

    @Column(name = "customer")
    private String customer;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private long amount;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;
}
