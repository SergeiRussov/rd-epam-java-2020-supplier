package com.epam.rd.domain;

import com.epam.rd.util.PaymentStatus;
import com.epam.rd.util.PaymentStatusesConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @Convert(converter = PaymentStatusesConverter.class)
    private PaymentStatus paymentStatus;

    private UUID invoiceId;
    private String storeCallbackUrl;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
