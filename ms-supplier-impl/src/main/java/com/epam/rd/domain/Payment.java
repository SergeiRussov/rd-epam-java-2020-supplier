package com.epam.rd.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    UUID id;

    @Column(name = "order_id")
    UUID orderId;

    @Column(name = "ogrn")
    private String ogrn;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "inn")
    private String inn;

    @Column(name = "r_s")
    private String r_s;

    @Column(name = "amount")
    private int amount;

    @Column(name = "acceptance_id")
    UUID acceptanceId;

    @Column(name = "payment_callback_url")
    private String paymentCallbackUrl;

    @Column(name = "status")
    private String status;

    @Column(name = "invoice_id")
    UUID invoiceId;

    @Column(name = "store_callback_url")
    private String store_callback_url;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "update_date")
    private Date updateDate;
}
