package com.epam.rd.service.impl;

import com.epam.rd.domain.Product;
import com.epam.rd.repository.PersistenceException;
import com.epam.rd.repository.ProductRepository;
import com.epam.rd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product find(UUID uuid) {
        return productRepository.findById(uuid).orElseThrow(() -> new PersistenceException(uuid.toString()));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(String name, String description, Long price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCreationDate(OffsetDateTime.now());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product update(UUID uuid, String name, String description, Long price) {
        Product product = find(uuid);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setUpdateDate(OffsetDateTime.now());
        productRepository.update(product);
        return product;
    }
}


