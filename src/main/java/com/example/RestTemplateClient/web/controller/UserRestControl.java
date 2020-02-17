package com.example.RestTemplateClient.web.controller;

import com.example.RestTemplateClient.web.model.Role;
import com.example.RestTemplateClient.web.model.User;
import com.example.RestTemplateClient.web.service.RoleService;
import com.example.RestTemplateClient.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping
public class UserRestControl {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users/{id}")
    public User get(@PathVariable Long id) {
        return userService.getUserByid(id);
    }

    @PostMapping(value = "/users/add")
    public User input(@ModelAttribute User user) {
        HashSet<Role> roles = new HashSet<>();
        for (Role r : user.getRoles()) {
            roles.add(roleService.getRole(r.getName()));
        }
        user.setRoles(roles);
        return userService.add(user);
    }

    @PostMapping(value = "/users/update")
    public User update(@ModelAttribute User user) {
        HashSet<Role> roles = new HashSet<>();
        for (Role r : user.getRoles()) {
            roles.add(roleService.getRole(r.getName()));
        }
        user.setRoles(roles);
        userService.update(user);
        return user;
    }

    @PostMapping(value = "/users/delete/{id}")
    public Boolean delete(@PathVariable Long id) {
        User user = userService.getUserByid(id);
        userService.delete(user);
        return true;
    }
}
