package com.epam.rd.repository;

import com.epam.rd.domain.OrderItem;
import com.epam.rd.domain.Payment;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * CRUD repository implementation for OrderItem entity.
 *
 * @author Aminev Ramil
 */
@Slf4j
public class OrderItemRepository implements Repository<OrderItem> {
     private final EntityManager entityManager = Persistence
            .createEntityManagerFactory("supplier-pu")
            .createEntityManager();

    /**
     * Trying to find {@code OrderItem} entity in persistence context using it's id.
     *
     * @param id of entity to find.
     * @return {@code Optional} with search result.
     */
    @Override
    public Optional<OrderItem> findById(UUID id) {
        log.info("Finding OrderItem entity by id={}", id);
        try {
            return Optional.ofNullable(entityManager.find(OrderItem.class, id));
        } catch (javax.persistence.PersistenceException e) {
            log.error(e.toString());
        }
        return Optional.empty();
    }

    /**
     * Trying to get all entities stored in repository.
     * If there is no OrderItems in repository or some error
     * occurs, method return empty list.
     *
     * @return list of OrderItems from repository.
     */
    @Override
    public List<OrderItem> findAll() {
        try {
            log.info("Finding all OrderItem entities in repository");
            Query findAllQuery = entityManager.createNativeQuery("select * from order_item;", OrderItem.class);
            return findAllQuery.getResultList();
        } catch (javax.persistence.PersistenceException e) {
            log.error(e.toString());
        }
        return Collections.emptyList();
    }

    /**
     * Saves given entity to persistence context. If specified orderItem
     * already in persistence context, then specified entity updating
     * entity that already exists.
     *
     * @param orderItem to save.
     */
    @Override
    public void save(OrderItem orderItem) {
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(Payment.class, orderItem.getId()) != null) {
                log.info("Updating entity in repository. Updated OrderItem: {}", orderItem);
                entityManager.merge(orderItem);
            } else {
                log.info("Inserting entity to repository. New OrderItem: {}", orderItem);
                entityManager.persist(orderItem);
            }
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (javax.persistence.PersistenceException e) {
            log.error(e.toString());
        }
    }

    /**
     * Removes given entity from persistence context.
     *
     * @param orderItem to delete from repository.
     */
    @Override
    public void delete(OrderItem orderItem) {
        if (entityManager.find(OrderItem.class, orderItem.getId()) != null) {
            try {
                log.info("Deleting OrderItem entity from repository: {}", orderItem);
                entityManager.getTransaction().begin();
                entityManager.remove(orderItem);
                entityManager.flush();
                entityManager.getTransaction().commit();
            } catch (PersistenceException e) {
                log.error(e.toString());
            }
        } else {
            log.info("Entity is not presented in repository: {}", orderItem);
        }
    }
}
