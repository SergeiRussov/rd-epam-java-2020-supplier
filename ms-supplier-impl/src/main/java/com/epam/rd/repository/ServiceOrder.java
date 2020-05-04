package com.epam.rd.repository;

import com.epam.rd.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ServiceOrder implements ServiceDB<Order> {
    private static EntityManager entityManager =
            Persistence.createEntityManagerFactory("supplier-pu").createEntityManager();

    @Override
    public List<Order> getAll() {
        Transaction tx = null;
        List<Order> orderList = new ArrayList<Order>();

        try {
            orderList = entityManager.createQuery("FROM Order", Order.class).getResultList();
        } catch (HibernateException e) {
            log.info(e.getMessage());
        }
        log.info("=== GET ALL ORDERS...");
        return orderList;
    }

    @Override
    public UUID create() {
        EntityTransaction tx = null;
        UUID uuid = null;
        Order order = new Order("customer", "status", 1000, ZonedDateTime.now());

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(order);
            entityManager.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage());
        }
        log.info("=== CREATED id={}", order.getId());
        return order.getId();
    }

    @Override
    public Order read(UUID uuid) {
        Order order = new Order();

        try {
            order = (Order) entityManager.find(Order.class, uuid);
        } catch (HibernateException e) {
            log.info(e.getMessage());
        }
        log.info("=== READED order={}", order);
        return order;
    }

    @Override
    public void update(Order order) {
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.persist(order);
            entityManager.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage());
        }
        log.info("=== UPDATED order={}", order);
    }

    @Override
    public void delete(UUID uuid) {
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.remove(entityManager.find(Order.class, uuid));
            entityManager.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage());
        }
        log.info("=== DELETED uuid={}", uuid);
    }
}
