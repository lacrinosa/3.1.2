package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public String showUser(Principal principal, Model model) {
        model.addAttribute("user", userServiceImpl.findByUsername(principal.getName()));
        return "user";
    }
}
