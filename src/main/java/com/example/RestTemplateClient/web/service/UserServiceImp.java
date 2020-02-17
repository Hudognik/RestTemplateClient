package com.example.RestTemplateClient.web.service;

import com.example.RestTemplateClient.web.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImp implements UserService {

    RestTemplate restTemplate = new RestTemplate();

    public User add(User user) {
        final String uri = "http://localhost:8088/users/add";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));

        return restTemplate.postForObject(uri, user, User.class);
    }


    public User[] listUsers() {
        final String uri = "http://localhost:8088/users";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));

        ResponseEntity<User[]> result = restTemplate.getForEntity(uri, User[].class);
        User[] users = result.getBody();

        return users;
    }


    public void delete(User user) {
        final String uri = "http://localhost:8088/users/delete/";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));
        String entityUrl = uri + user.getId();
        restTemplate.delete(entityUrl);
    }


    public void update(User user) {
        final String uri = "http://localhost:8088/users/update";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));

        restTemplate.put(uri, user, User.class);
    }

    public User getUserByName(String username) {
        final String uri = "http://localhost:8088/users/get/";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));

        return restTemplate.getForObject(uri + username, User.class);
    }


    public User getUserByid(Long id) {
        final String uri = "http://localhost:8088/users/";
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));

        return restTemplate.getForObject(uri + id, User.class);
    }
}
