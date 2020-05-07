package com.epam.rd.service;

import com.epam.rd.domain.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    /**
     * Найти продукцию по идентификатору
     * @param uuid идентификатор продукции
     * @return {@link Product} продукция
     */
    Product find(UUID uuid);

    /**
     * Найти весь перечень продукции
     * @return {@link List<Product>} продукция
     */
    List<Product> findAll();

    /**
     * Метод создания продукции
     * @param name наименование продукции
     * @param description описание продукции
     * @param price цена
     */
    void save(String name, String description, Long price);

    /**
     * Метод обновления продукции
     * @param uuid идентификатор продукции
     * @param name наименование продукции
     * @param description описание продукции
     * @param price цена
     */
    void update(UUID uuid, String name, String description, Long price);
}

