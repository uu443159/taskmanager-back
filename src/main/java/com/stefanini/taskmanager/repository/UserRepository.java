package com.stefanini.taskmanager.repository;

import com.stefanini.taskmanager.model.User;

public interface UserRepository extends GenericRepository<User> {

    User findByName(String userName);

    User findByLoginAndPass(String userName, String password);
}
