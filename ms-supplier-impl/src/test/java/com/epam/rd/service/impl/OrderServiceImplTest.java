package com.epam.rd.service.impl;

import com.epam.rd.domain.Order;
import com.epam.rd.domain.OrderStatus;
import com.epam.rd.domain.Product;
import com.epam.rd.repository.OrderRepository;
import com.epam.rd.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private PaymentServiceImpl paymentService;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void createReturnsUuid() {
        Map<UUID, Integer> order = new HashMap<>();
        order.put(UUID.fromString("27c7f1a1-2cb9-45ab-a006-5978feb97177"), 2);
        Product product = new Product();
        product.setId(UUID.fromString("27c7f1a1-2cb9-45ab-a006-5978feb97177"));
        when(productRepository.findById(any())).
                thenReturn(Optional.ofNullable(product));
        when(paymentService.create(any())).
                thenReturn(UUID.fromString("a2048f7c-0c7d-46c7-afe8-ed9ee7ffffff"));

        UUID uuid = orderService.create(order);

        verify(productRepository).findById(UUID.fromString("27c7f1a1-2cb9-45ab-a006-5978feb97177"));
        verify(orderRepository).save(any());
        assertEquals(uuid, UUID.fromString("a2048f7c-0c7d-46c7-afe8-ed9ee7ffffff"));
    }

    @Test
    public void markAsPaidSetsPaidStatus() {
        Order testOrder = new Order();
        testOrder.setStatus(OrderStatus.NEW);
        when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(testOrder));

        assertEquals(testOrder.getStatus(), OrderStatus.NEW);
        orderService.markAsPaid(UUID.fromString("a2048f7c-0c7d-46c7-afe8-ed9ee7670f04"));
        assertEquals(testOrder.getStatus(), OrderStatus.PAID);
        verify(orderRepository).findById(any());
        verify(orderRepository).save(any());
    }
}