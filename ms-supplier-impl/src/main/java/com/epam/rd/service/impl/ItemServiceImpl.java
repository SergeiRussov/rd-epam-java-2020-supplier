package com.epam.rd.service.impl;

import com.epam.rd.domain.Item;
import com.epam.rd.domain.Order;
import com.epam.rd.domain.OrderItem;
import com.epam.rd.domain.Product;
import com.epam.rd.repository.*;
import com.epam.rd.service.ItemService;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository = new ItemRepository();
    private OrderItemRepository orderItemRepository = new OrderItemRepository();
    private ProductRepository productRepository = new ProductRepository();
    private OrderRepository orderRepository = new OrderRepository();

    @Override
    public Item find(UUID uuid) {
        return itemRepository.findById(uuid).orElseThrow(() -> new PersistenceException(uuid.toString()));
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item create(UUID productUUID, UUID orderUUID) {
        Optional product = productRepository.findById(productUUID);
        Optional order = orderRepository.findById(orderUUID);
        if (product.isEmpty()) {
            log.warn("Product with specified UUID is not presented in repository: {}", productUUID);
            return null;
        }
        if (order.isEmpty()) {
            log.warn("Order with specified UUID is not presented in repository: {}", orderUUID);
            return null;
        }
        Item newItem = new Item();
        newItem.setProduct((Product) product.get());
        newItem.setOrder((Order) order.get());
        newItem.setUpdateDate(OffsetDateTime.now());
        newItem.setCreationDate(OffsetDateTime.now());
        return itemRepository.save(newItem);
    }

    @Override
    public List<Item> findOrderItems(UUID orderUUID) {
        Optional optOrder = orderRepository.findById(orderUUID);
        if (optOrder.isEmpty()){
            return new ArrayList<Item>();
        }
        Order order = (Order) optOrder.get();
        return new ArrayList<Item>();
    }
}
