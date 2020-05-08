package com.epam.rd.repository;

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
 * CRUD repository implementation for Payment entity.
 *
 * @author Aminev Ramil
 */
@Slf4j
public class PaymentRepository implements Repository<Payment> {
    private final EntityManager entityManager = Persistence
            .createEntityManagerFactory("supplier-pu")
            .createEntityManager();

    /**
     * Trying to find {@code Payment} entity in persistence context using it's id.
     *
     * @param id of entity to find.
     * @return {@code Optional} with search result.
     */
    @Override
    public Optional<Payment> findById(UUID id) {
        log.info("Finding Item entity by id={}", id);
        try {
            return Optional.ofNullable(entityManager.find(Payment.class, id));
        } catch (PersistenceException e) {
            log.error(e.toString());
        }
        return Optional.empty();
    }

    /**
     * Trying to get all entities stored in repository.
     * If there is no Payments in repository or some error
     * occurs, method return empty list.
     *
     * @return list of Payments from repository.
     */
    @Override
    public List<Payment> findAll() {
        try {
            log.info("Finding all Item entities in repository");
            Query findAllQuery = entityManager.createNativeQuery("select * from payment;", Payment.class);
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
     * @param payment to save.
     */
    @Override
    public Payment save(Payment payment) {
        try {
            entityManager.getTransaction().begin();
            if (entityManager.find(Payment.class, payment.getId()) != null) {
                log.info("Updating entity in repository. Updated Payment: {}", payment);
                entityManager.merge(payment);
            } else {
                log.info("Inserting entity to repository. New Payment: {}", payment);
                entityManager.persist(payment);
            }
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            log.error(e.toString());
        }
        return payment;
    }

    /**
     * Removes given entity from persistence context.
     *
     * @param payment to delete from repository.
     */
    @Override
    public void delete(Payment payment) {
        if (entityManager.find(Payment.class, payment.getId()) != null) {
            try {
                log.info("Deleting Payment entity from repository: {}", payment);
                entityManager.getTransaction().begin();
                entityManager.remove(payment);
                entityManager.flush();
                entityManager.getTransaction().commit();
            } catch (PersistenceException e) {
                log.error(e.toString());
            }
        } else {
            log.info("Entity is not presented in repository: {}", payment);
        }
    }
}
