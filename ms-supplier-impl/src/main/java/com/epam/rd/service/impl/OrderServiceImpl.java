package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.OrderItem;
import com.epam.rd.domain.OrderStatus;
import com.epam.rd.repository.OrderItemRepository;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.ProductRepository;
import com.epam.rd.service.OrderService;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.*;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository = new OrderRepository();
    OrderItemRepository orderItemRepository = new OrderItemRepository();
    ProductRepository productRepository = new ProductRepository();
    PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Override
    public UUID create(Map<UUID, Integer> order) {
        Order newOrder = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        newOrder.setCreationDate(ZonedDateTime.now());
        newOrder.setUpdateDate(ZonedDateTime.now());
        newOrder.setStatus(OrderStatus.NEW);
        for (UUID key : order.keySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setCreationDate(OffsetDateTime.now());
            orderItem.setUpdateDate(OffsetDateTime.now());
            orderItem.setOrder(newOrder);
            orderItem.setProduct(productRepository.findById(key).get());
            orderItem.setAmount(order.get(key));
            orderItems.add(orderItem);
        }
        newOrder.setOrderItems(orderItems);
        orderRepository.save(newOrder);
        return paymentService.create(newOrder.getId());
    }

    @Override
    public void markAsPaid(UUID uuid) {
        Optional optOrder = orderRepository.findById(uuid);
        if (optOrder.isPresent()) {
            Order order = (Order) optOrder.get();
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }
    }
}
