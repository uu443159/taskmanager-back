package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.repository.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericRepositoryImpl<T> implements GenericRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();

        System.out.println("dao");
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
        Root<T> rootEntry = cq.from(getEntityClass());
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void deleteById(long id) {
        T entity = entityManager.find(getEntityClass(), id);

        entityManager.remove(entity);
        entityManager.flush();
    }

    protected abstract String getQuery();
}
