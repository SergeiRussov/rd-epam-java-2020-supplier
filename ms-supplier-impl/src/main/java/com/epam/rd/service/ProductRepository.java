package com.epam.rd.service;

import com.epam.rd.domain.Product;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

@Slf4j
public class ProductRepository {
    private static final String EXCEPTION = "Произошло исключение при обработке команды ";
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("supplier-pu");
    EntityManager manager = emf.createEntityManager();

    public ProductRepository() {
    }

    public Product findById(UUID id) {
        try {
            Product product = manager.find(Product.class, id);
            return product;
        } catch (CommandProcessingException e) {
            log.debug(EXCEPTION, e);
        }
        return null;
    }

    public void delete(UUID id) {
        Product product = manager.find(Product.class, id);
        manager.getTransaction().begin();
        try {
            manager.remove(product);
        } catch (CommandProcessingException e) {
            log.debug(EXCEPTION, e);
        }
        manager.getTransaction().commit();
    }

    public void updateName(UUID id, String name) {
        Product product = manager.find(Product.class, id);
        manager.getTransaction().begin();
        try {
            product.setName(name);
        } catch (CommandProcessingException e) {
            log.debug(EXCEPTION, e);
        }
        manager.getTransaction().commit();
    }

    public void insert(Product product) {
        manager.getTransaction().begin();
        try {
            manager.persist(product);
        } catch (CommandProcessingException e) {
            log.debug(EXCEPTION, e);
        }
        manager.getTransaction().commit();
    }
}
