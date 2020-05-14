package com.epam.rd.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The {@code Repository} interface is CRUD implementation.
 *
 * @author Aminev Ramil
 */
public interface Repository<T> {
    /**
     * Finding domain entity in repository using it's id.
     *
     * @param id of entity to find.
     * @return {@code Optional} which may contain found entity.
     */
    Optional<T> findById(UUID id);

    /**
     * Fetching list that contain all related entities.
     * If there is no entities in repository or some error
     * occurs, method return empty list.
     *
     * @return list which may contains entities.
     */
    List<T> findAll();

    /**
     * Saving or updating entity in repository.
     *
     * @param entity to save or update
     */
    T save(T entity);

    /**
     * Deleting entity from repository.
     *
     * @param entity to delete
     */
    void delete(T entity);
}
