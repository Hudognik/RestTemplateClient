package com.example.RestTemplateClient.web.service;

import com.example.RestTemplateClient.web.model.Role;
import com.example.RestTemplateClient.web.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleService {

    public Role[] roleList() {
        final String uri = "http://localhost:8088/roles";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));

        ResponseEntity<Role[]> result = restTemplate.getForEntity(uri, Role[].class);

        return result.getBody();
    }

    public Role getRole(Long id) {
        final String uri = "http://localhost:8088/roles/";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));

        Role result = restTemplate.getForObject(uri + id, Role.class);


        return result;
    }

    public Role getRole(String name) {
        final String uri = "http://localhost:8088/roles/get/";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("admin", "admin"));

        Role result = restTemplate.getForObject(uri + name, Role.class);


        return result;
    }
}
