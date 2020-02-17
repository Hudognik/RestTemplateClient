package com.example.RestTemplateClient.web.service;

import com.example.RestTemplateClient.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImp userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByName(s);
        } catch (InternalAuthenticationServiceException e) {
            e.printStackTrace();
        }

        return user;
    }
}
