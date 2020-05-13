package com.epam.rd.repository;

import com.epam.rd.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class OrderRepository implements Repository<Order> {
    private static final EntityManager entityManager =
            Persistence.createEntityManagerFactory("supplier-pu").createEntityManager();

    @Override
    public Optional<Order> findById(UUID id) {

        log.info("=== FINDING id={}", id);
        try {
            return Optional.ofNullable(entityManager.find(Order.class, id));
        } catch (HibernateException e) {
            log.info(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        Transaction tx = null;
        List<Order> orderList = new ArrayList<Order>();

        try {
            orderList = entityManager.createQuery("FROM Order", Order.class).getResultList();
        } catch (HibernateException e) {
            log.info(e.getMessage());
        }
        log.info("=== FIND ALL ORDERS...");
        return orderList;
    }

    @Override
    public Order save(Order entity) {
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(entity);
            entityManager.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage());
        }
        log.info("=== SAVED entity={}", entity);
        return entity;
    }

    @Override
    public void delete(Order entity) {
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.remove(entity);
            entityManager.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage());
        }
        log.info("=== DELETED entity={}", entity);
    }
}
