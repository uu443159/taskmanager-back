package com.stefanini.taskmanager.repository.impl;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

    private PasswordEncoder passwordEncoder;

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User findByName(String userName) {
        Query query = entityManager.createQuery("SELECT r FROM " + getEntityClass().getSimpleName() + " r WHERE r.userName=:userName");
        query.setParameter("userName", userName);
        User user = (User) query.getSingleResult();

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
