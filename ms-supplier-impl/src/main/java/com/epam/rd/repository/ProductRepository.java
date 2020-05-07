package com.epam.rd.repository;


import com.epam.rd.domain.Product;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class ProductRepository implements Repository<Product> {
    private static final String EXCEPTION = "Произошло исключение при обработке команды ";
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("supplier-pu");
    private EntityManager manager = emf.createEntityManager();

    @Override
    public Optional<Product> findById(UUID id) {
        log.info("Finding Product entity by id={}", id);
        try {
            return Optional.ofNullable(manager.find(Product.class, id));
        } catch (PersistenceException e) {
            log.debug(EXCEPTION, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        log.info("Finding all Product entities in repository");
        try {
            Query findAllQuery = manager.createNativeQuery("select * from item;", Product.class);
            return findAllQuery.getResultList();
        } catch (PersistenceException e) {
            log.debug(EXCEPTION, e);
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(Product product) {
        if (manager.find(Product.class, product.getId()) != null) {
            manager.getTransaction().begin();
            try {
                manager.remove(product);
            } catch (PersistenceException e) {
                log.debug(EXCEPTION, e);
            }
            manager.getTransaction().commit();
        } else {
            log.info("Entity is not presented in repository: {}", product);
        }
    }

    @Override
    public void save(Product product) {
        try {
            manager.getTransaction().begin();
            if (manager.find(Product.class, product.getId()) != null) {
                log.info("Updating entity in repository. Updated Product: {}", product);
                manager.merge(product);
            } else {
                log.info("Inserting entity to repository. New Product: {}", product);
                manager.persist(product);
            }
            manager.flush();
            manager.getTransaction().commit();
        } catch (javax.persistence.PersistenceException e) {
            log.debug(EXCEPTION, e);
        }
    }

    public void update(UUID id, String name, String description, Long price) {
        Product product = manager.find(Product.class, id);
        manager.getTransaction().begin();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        manager.getTransaction().commit();
        manager.close();
    }
}


