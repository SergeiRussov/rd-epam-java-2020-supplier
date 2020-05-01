package com.epam.rd.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    private String ogrn;
    private String kpp;
    private String inn;
    private String rS;
    private long amount;
    private UUID acceptanceId;
    private String paymentCallbackUrl;
    private String status;
    private UUID invoiceId;
    private String storeCallbackUrl;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
