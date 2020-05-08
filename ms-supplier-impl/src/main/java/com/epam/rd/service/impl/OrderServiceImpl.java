package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.OrderItem;
import com.epam.rd.repository.OrderItemRepository;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.ProductRepository;
import com.epam.rd.service.OrderService;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    @Override
    public UUID create(Map<UUID, Integer> order) {
        OrderRepository orderRepository = new OrderRepository();
        OrderItemRepository orderItemRepository = new OrderItemRepository();
        ProductRepository productRepository = new ProductRepository();
        Order newOrder = new Order();
        newOrder.setCreationDate(ZonedDateTime.now());
        newOrder.setUpdateDate(ZonedDateTime.now());
        newOrder.setStatus("not_paid");
        orderRepository.save(newOrder);
        for (UUID key : order.keySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCreationDate(OffsetDateTime.now());
            orderItem.setUpdateDate(OffsetDateTime.now());
            orderItem.setOrder(newOrder);
            orderItem.setProduct(productRepository.findById(key).get());
            orderItem.setAmount(order.get(key));
            orderItemRepository.save(orderItem);
        }
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        return paymentService.create(newOrder.getId());
    }

    @Override
    public void markAsPaid(UUID uuid) {
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.findById(uuid).get();
        order.setStatus("paid");
        orderRepository.save(order);
    }
}
