package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    private PasswordEncoder passwordEncoder;

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected String getQuery() {
        return "SELECT id, first_name, last_name, user_name FROM ";
    }

    @Override
    public User findByName(String userName) {
        User user = entityManager.find(getEntityClass(), userName);
        if (user == null) {
            throw new EntityNotFoundException("Can't find " + getEntityClass().getSimpleName() + " for user name = " + userName);
        }

        return user;
    }

    @Override
    public User findByLoginAndPass(String userName, String password) {
        User user = findByName(userName);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
