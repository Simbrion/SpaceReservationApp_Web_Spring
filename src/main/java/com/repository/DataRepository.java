package com.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Getter
@RequiredArgsConstructor
@Transactional
@Repository
abstract class DataRepository<T> {

    @PersistenceContext
    private final EntityManager entityManager;

    boolean isEmpty (Query query) {
            Long result = (Long) query.getSingleResult();
            return result == 0;
    }

    public void addEntity(T entity) {
            entityManager.persist(entity);
    }

}
