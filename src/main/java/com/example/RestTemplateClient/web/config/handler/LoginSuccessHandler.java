package com.example.RestTemplateClient.web.config.handler;

import com.example.RestTemplateClient.web.model.User;
import com.example.RestTemplateClient.web.service.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final RoleService roleService;

    public LoginSuccessHandler(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException {

        User user = (User) authentication.getPrincipal();
        if (user == null) {
            response.sendRedirect("/login");
        }
        if (user.getRoles().contains(roleService.getRole("ROLE_ADMIN"))) {
            response.sendRedirect("/admin");
        }else if (user.getRoles().contains(roleService.getRole("ROLE_USER"))) {
            response.sendRedirect("/userinfo");
        }
    }
}

