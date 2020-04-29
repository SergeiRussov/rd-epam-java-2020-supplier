package com.epam.rd.service;

import com.epam.rd.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

public class ProductStorage {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("supplier-pu");

    public ProductStorage() {
    }

    public Product findById(UUID id) {
        EntityManager manager = emf.createEntityManager();
        Product product = manager.find(Product.class, id);
        return product;
    }

    public void delete(UUID id) {
        EntityManager manager = emf.createEntityManager();
        Product product = manager.find(Product.class, id);
        manager.getTransaction().begin();
        manager.remove(product);
        manager.getTransaction().commit();
        manager.close();

    }

    public void updateName(UUID id, String name) {
        EntityManager manager = emf.createEntityManager();
        Product product = manager.find(Product.class, id);
        manager.getTransaction().begin();
        product.setName(name);
        manager.getTransaction().commit();
        manager.close();
    }

    public void insert(Product product) {
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(product);
        manager.getTransaction().commit();
        manager.close();

    }
}
