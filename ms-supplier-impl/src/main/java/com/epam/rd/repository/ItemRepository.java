package com.epam.rd.repository;

import com.epam.rd.domain.Item;
import com.epam.rd.domain.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * CRUD repository implementation for Item entity.
 *
 * @author Aminev Ramil
 */
@Slf4j
@Repository
public class ItemRepository implements CommonRepository<Item> {
    private final EntityManager entityManager = Persistence
            .createEntityManagerFactory("supplier-pu")
            .createEntityManager();

    /**
     * Trying to find {@code Item} entity in persistence context using it's id.
     *
     * @param id of entity to find.
     * @return {@code Optional} with search result.
     */
    @Override
    public Optional<Item> findById(UUID id) {
        log.info("Finding Item entity by id={}", id);
        try {
            return Optional.ofNullable(entityManager.find(Item.class, id));
        } catch (PersistenceException e) {
            log.error(e.toString());
        }
        return Optional.empty();
    }

    /**
     * Trying to get all entities stored in repository.
     * If there is no Items in repository or some error
     * occurs, method return empty list.
     *
     * @return list of Items from repository.
     */
    @Override
    public List<Item> findAll() {
        try {
            log.info("Finding all Item entities in repository");
            Query findAllQuery = entityManager.createNativeQuery("select * from item;", Item.class);
            return findAllQuery.getResultList();
        } catch (PersistenceException e) {
            log.error(e.toString());
        }
        return Collections.emptyList();
    }

    /**
     * Saves given entity to persistence context. If specified item
     * already in persistence context, then specified entity updating
     * entity that already exists.
     *
     * @param item to save.
     */
    @Override
    public Item save(Item item) {
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(Payment.class, item.getId()) != null) {
                log.info("Updating entity in repository. Updated Item: {}", item);
                entityManager.merge(item);
            } else {
                log.info("Inserting entity to repository. New Item: {}", item);
                entityManager.persist(item);
            }
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            log.error(e.toString());
        }
        return item;
    }

    /**
     * Removes given entity from persistence context.
     *
     * @param item to delete from repository.
     */
    @Override
    public void delete(Item item) {
        if (entityManager.find(Item.class, item.getId()) != null) {
            try {
                log.info("Deleting Item entity from repository: {}", item);
                entityManager.getTransaction().begin();
                entityManager.remove(item);
                entityManager.flush();
                entityManager.getTransaction().commit();
            } catch (PersistenceException e) {
                log.error(e.toString());
            }
        } else {
            log.info("Entity is not presented in repository: {}", item);
        }
    }
}
