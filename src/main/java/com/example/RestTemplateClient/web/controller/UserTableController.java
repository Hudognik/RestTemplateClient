package com.example.RestTemplateClient.web.controller;

import com.example.RestTemplateClient.web.model.User;
import com.example.RestTemplateClient.web.service.RoleService;
import com.example.RestTemplateClient.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class UserTableController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin")
    public String printTable(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("roles", roleService.roleList());
        return "admin";
    }

    @GetMapping(value = "/login")
    public String loginPage(HttpSession session, HttpServletRequest request) {
        session.setAttribute("username", request.getParameter("j_username"));
        return "login";
    }

    @PostMapping(value = "/login")
    public String sendName() {
        return "login";
    }

    @GetMapping(value = "/userinfo")
    public String userInfo(Model model, @AuthenticationPrincipal User userFromSession) {
        model.addAttribute("user", userFromSession);
        return "userinfo";
    }
}