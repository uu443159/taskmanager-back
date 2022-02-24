package com.stefanini.taskmanager.controller;

import com.stefanini.taskmanager.model.User;
import com.stefanini.taskmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public String addNewUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String userName) {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);

        userService.addUser(user);

        return "Saved";
    }

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
