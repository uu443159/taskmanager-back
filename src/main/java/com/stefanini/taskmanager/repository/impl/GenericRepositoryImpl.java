package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();

        return entity;
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
        entityManager.flush();
    }

    @Override
    public T findById(long id) {
        T entity = entityManager.find(getEntityClass(), id);
        if (entity == null) {
            throw new EntityNotFoundException("Can't find " + getEntityClass().getSimpleName() + " for ID = " + id);
        }

        return entity;
    }

    @Override
    public List<T> findAll() {
        return entityManager.createNativeQuery(getQuery() + getEntityClass().getSimpleName()).getResultList();
    }

    @Override
    public void deleteById(long id) {
        T entity = entityManager.find(getEntityClass(), id);

        entityManager.remove(entity);
        entityManager.flush();
    }

    protected abstract String getQuery();
}
