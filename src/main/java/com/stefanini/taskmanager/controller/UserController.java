package com.stefanini.taskmanager.controller;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.model.UserRole;
import com.stefanini.taskmanager.repository.UserRoleRepository;
import com.stefanini.taskmanager.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("/save")
//    public String addNewUser(@RequestBody User user) {
//        UserRole userRole = userRoleRepository.findById(2);
//
//        user.setUserRole(userRole);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userService.addUser(user);
//
//        return "Saved";
//    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable(value = "id") Long userId, @RequestParam String firstName, @RequestParam String lastName) {
        User user = userService.showUserById(userId);

        user.setFirstName(firstName);
        user.setLastName(lastName);

        userService.updateUser(user);

        return "Updated";

    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user) {
        userService.removeUser(user);

        return "Deleted";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userService.showUserById(userId);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.showAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable(value = "id") Long userId) {
        userService.removeUserById(userId);

        return "Deleted";
    }
}
