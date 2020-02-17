package com.example.RestTemplateClient.web.service;



import com.example.RestTemplateClient.web.model.User;

public interface UserService {
    User add(User user);

    User[] listUsers();

    void delete(User user);

    void update(User user);

    User getUserByName(String username);

    User getUserByid(Long id);
}
