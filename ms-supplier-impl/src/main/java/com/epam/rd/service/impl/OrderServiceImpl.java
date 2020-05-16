package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.OrderItem;
import com.epam.rd.domain.OrderStatus;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.ProductRepository;
import com.epam.rd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    ProductRepository productRepository;
    PaymentServiceImpl paymentService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, PaymentServiceImpl paymentService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.paymentService = paymentService;
    }

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
        Optional<Order> optOrder = orderRepository.findById(uuid);
        if (optOrder.isPresent()) {
            Order order = optOrder.get();
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }
    }
}
