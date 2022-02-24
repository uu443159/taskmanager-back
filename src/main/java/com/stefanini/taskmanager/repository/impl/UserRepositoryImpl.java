package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected String getQuery() {
        return "SELECT id, first_name, last_name, user_name FROM ";
    }
}
