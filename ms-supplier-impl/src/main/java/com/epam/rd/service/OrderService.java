package com.epam.rd.service;

import java.util.Map;
import java.util.UUID;

public interface OrderService {

    /**
     * Метод создания заказа
     * @param order содержимое заказа. Ключом является UUID товара, значением количество.
     * @return UUID платежа из платежной системы.
     */
    UUID create(Map<UUID, Integer> order);

    /**
     * Пометить заказ как оплаченный
     * @param uuid идентификатор заказа
     */
    void markAsPaid(UUID uuid);
}
