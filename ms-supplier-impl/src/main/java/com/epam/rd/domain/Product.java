package com.epam.rd.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @OneToMany(mappedBy = "product")
    private Set<Item> items;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private long price;

    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

    @Column(name = "update_date")
    private OffsetDateTime updateDate;
}


