package com.epam.rd.service.impl;

import com.epam.rd.domain.Product;
import com.epam.rd.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository = new ProductRepository();

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void findById() {
        String uuidStr = "bf8f42d7-6fa6-46e2-a0ff-c657840d673a";
        Product product = new Product();
        UUID uuid = UUID.fromString(uuidStr);
        when(productRepository.findById(uuid)).thenReturn(Optional.of(product));
        productService.find(uuid);
        verify(productRepository, times(1)).findById(uuid);
    }

    @Test
    public void findAll() {
        productService.findAll();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void save() {
        String name = "ProductName";
        String description = "Product description";
        Long price = 125487L;
        Product product = productService.save(name, description, price);
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertNotNull(product.getCreationDate());
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void update() {
        String uuidStr = "bf8f42d7-6fa6-46e2-a0ff-c657840d673a";
        UUID uuid = UUID.fromString(uuidStr);
        String name = "ProductName";
        String description = "Product description";
        Product product = new Product();
        product.setId(uuid);
        Long price = 125487L;
        when(productRepository.findById(uuid)).thenReturn(Optional.of(product));
        product = productService.update(uuid, name, description, price);
        assertEquals(uuid, product.getId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertNotNull(product.getUpdateDate());
        verify(productRepository, times(1)).update(any());
    }
}