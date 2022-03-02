package com.stefanini.taskmanager.service.impl;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.model.UserRole;
import com.stefanini.taskmanager.repository.UserRepository;
import com.stefanini.taskmanager.repository.UserRoleRepository;
import com.stefanini.taskmanager.security.RegistrationRequest;
import com.stefanini.taskmanager.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void addUser(RegistrationRequest registrationRequest) {

        User user = new User();
        UserRole userRole = userRoleRepository.findByRoleName(registrationRequest.getRoleName());

        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setUserName(registrationRequest.getUserName());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());

        user.setUserRole(userRole);
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
