package com.epam.rd.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * POJO class {@code OrderItem} represents domain entity from order_item table
 *
 * @author Aminev Ramil
 */

@Entity
@Table(name = "order_item")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private long amount;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
