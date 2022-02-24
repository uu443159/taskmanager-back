package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void removeUser(User user);

    User showUserById(long id);

    List<User> showAllUsers();

    void removeUserById(long id);

}
