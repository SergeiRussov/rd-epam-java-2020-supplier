package com.epam.rd.service.impl;

import com.epam.rd.domain.Item;
import com.epam.rd.domain.Order;
import com.epam.rd.domain.Product;
import com.epam.rd.repository.ItemRepository;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    void find() {
        when(itemRepository.findById(any())).thenReturn(Optional.ofNullable(new Item()));
        itemService.find(UUID.randomUUID());
        verify(itemRepository).findById(any());
    }

    @Test
    void findAll() {
        when(itemRepository.findAll()).thenReturn(null);
        itemService.findAll();
        verify(itemRepository).findAll();
    }

    @Test
    void create() {
        UUID productUUID = UUID.randomUUID();
        UUID orderUUID = UUID.randomUUID();
        Product product = new Product();
        product.setId(productUUID);
        Order order = new Order();
        order.setId(orderUUID);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
        when(itemRepository.save(any())).thenReturn(null);

        Item item = itemService.create(productUUID, orderUUID);
        verify(productRepository).findById(any());
        verify(orderRepository).findById(any());
        verify(itemRepository).save(any());
        assertNull(item);
    }

    @Test
    void createWithNoProductUUID() {
        UUID productUUID = UUID.randomUUID();
        UUID orderUUID = UUID.randomUUID();
        Order order = new Order();
        order.setId(orderUUID);

        when(productRepository.findById(any())).thenReturn(Optional.empty());
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        Item item = itemService.create(productUUID, orderUUID);
        verify(productRepository).findById(any());
        verify(orderRepository).findById(any());
        verify(itemRepository, times(0)).save(any());
        assertNull(item);
    }

    @Test
    void createWithNoOrderUUID() {
        UUID productUUID = UUID.randomUUID();
        UUID orderUUID = UUID.randomUUID();
        Product product = new Product();
        product.setId(productUUID);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        Item item = itemService.create(productUUID, orderUUID);
        verify(productRepository).findById(any());
        verify(orderRepository).findById(any());
        verify(itemRepository, times(0)).save(any());
        assertNull(item);
    }

    @Test
    void findOrderItemsWithNoOrderUUID() {
        Order order = new Order();
        order.setItems(new ArrayList<>());

        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        List<Item> itemList = itemService.findOrderItems(UUID.randomUUID());
        verify(orderRepository).findById(any());
        assertTrue(itemList.isEmpty());
    }

    @Test
    void findOrderItems() {
        Order order = new Order();
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item());
        itemList.add(new Item());
        order.setItems(itemList);

        when(orderRepository.findById(any())).thenReturn(Optional.of(order));

        List<Item> itemList1 = itemService.findOrderItems(UUID.randomUUID());
        verify(orderRepository).findById(any());
        assertEquals(itemList1.size(), 2);
    }
}