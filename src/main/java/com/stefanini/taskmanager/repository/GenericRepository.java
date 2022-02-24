package com.stefanini.taskmanager.repository;

import java.util.List;

public interface GenericRepository<T> {

    T create(T entity);

    void update(T entity);

    void delete(T entity);

    T findById(long id);

    List<T> findAll();

    void deleteById(long id);

}
