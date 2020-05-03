package com.epam.rd.service.impl;

import com.epam.rd.domain.Product;
import com.epam.rd.service.ProductService;

import java.util.List;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {
    @Override
    public Product find(UUID uuid) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product save(String name, String description, Long price) {
        return null;
    }

    @Override
    public Product update(UUID uuid, String name, String description, Long price) {
        return null;
    }
}
