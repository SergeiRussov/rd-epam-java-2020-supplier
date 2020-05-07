package com.epam.rd.service.impl;

import com.epam.rd.domain.Product;
import com.epam.rd.repository.PersistenceException;
import com.epam.rd.repository.ProductRepository;
import com.epam.rd.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product find(UUID uuid) {

        return productRepository.findById(uuid).orElseThrow(() -> new PersistenceException(uuid.toString()));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public void save(String name, String description, Long price) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        productRepository.save(product);
    }

    @Override
    public void update(UUID uuid, String name, String description, Long price) {
        productRepository.update(uuid, name, description, price);

    }

}
