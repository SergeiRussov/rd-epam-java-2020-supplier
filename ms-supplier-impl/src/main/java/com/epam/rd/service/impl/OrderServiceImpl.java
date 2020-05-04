package com.epam.rd.service.impl;

import com.epam.rd.domain.Item;
import com.epam.rd.domain.Order;
import com.epam.rd.repository.ItemRepository;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.ProductRepository;
import com.epam.rd.service.OrderService;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
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
            orderItem.setCreationDate(ZonedDateTime.now());
            orderItem.setUpdateDate(ZonedDateTime.now());
            orderItem.setOrder(newOrder);
            orderItem.setProduct(productRepository.findById(key));
            orderItem.setQuantity(order.get(key));
            orderItemRepository.save(orderItem);
        }
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        return paymentService.create(newOrder.getId());
    }

    @Override
    public void markAsPaid(UUID uuid) {
        OrderRepository orderRepository = new OrderRepository();
        Optional order = orderRepository.findById(uuid);
        Order newOrder = (Order) order.get();
        newOrder.setStatus("paid");
        orderRepository.save(newOrder);
    }
}
