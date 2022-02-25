package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepository.create(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    @Transactional
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User showUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User showUserByName(String userName) throws UsernameNotFoundException {
        return userRepository.findByName(userName);
    }

    @Override
    public User showUserByNameAndPass(String userName, String pass) {
        return userRepository.findByLoginAndPass(userName, pass);
    }
}
