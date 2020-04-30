package com.epam.rd.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

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

    public Order(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }


    public String getStatus() {
        return status;
    }

    public long getAmount() {
        return amount;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setUpdateDate(ZonedDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
