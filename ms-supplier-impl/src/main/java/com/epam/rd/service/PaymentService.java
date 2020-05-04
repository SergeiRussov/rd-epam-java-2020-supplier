package com.epam.rd.service;

import java.util.UUID;

public interface PaymentService {

    /**
     * Создание платежа
     * @param orderUUID идентификатор заказа
     * @return {@link UUID} идентификатор оплаты из платежной системы
     */
    UUID create(UUID orderUUID);

    /**
     * Обновление статуса оплаты заказа
     * @param paymentUUID идентификатор оплаты
     */
    void updateStatus(UUID paymentUUID);
}
