package com.stefanini.taskmanager.service;

import com.stefanini.taskmanager.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void removeUser(User user);

    User showUserById(long id);

    List<User> showAllUsers();

    void removeUserById(long id);

    User showUserByName(String userName) throws UsernameNotFoundException;

    User showUserByNameAndPass(String userName, String pass);

}
