package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.model.Task;
import com.stefanini.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TaskRepositoryImpl extends GenericRepositoryImpl<Task> implements TaskRepository {

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    @Override
    public List<Task> getTasksByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT t FROM " + getEntityClass().getSimpleName() + " t WHERE t.userName=:userName");
        query.setParameter("userName", userName);

        return query.getResultList();
    }
}
