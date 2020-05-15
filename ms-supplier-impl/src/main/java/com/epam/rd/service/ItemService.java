package com.epam.rd.service;

import com.epam.rd.domain.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    /**
     * Найти продукцию по идентификатору
     * @param uuid идентификатор продукции
     * @return {@link Item} продукция
     */
    Item find(UUID uuid);

    /**
     * Найти весь перечень товара
     * @return {@link List <Item>} продукция
     */
    List<Item> findAll();

    /**
     * Создать товар для заказа
     * @param productUUID продукция на основании которой будет создан товар
     * @param orderUUID заказ для которого создан товар
     * @return {@link Item}
     */
    Item create(UUID productUUID, UUID orderUUID);

    /**
     * Поиск всех товаров по идентификатору заказа
     * @param uuid идентификатор заказа
     * @return {@link List<Item>}
     */
    List<Item> findOrderItems(UUID uuid);
}
