package com.goit.goit_notes.controller;

import jakarta.servlet.ServletRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    @Secured({"USER"})
    public String users(ServletRequest request, Authentication authentication) {

        return "index_user";
    }
    @GetMapping("/users/admin")
    @Secured({"ADMIN"})
    public String redirectByRoles(ServletRequest request, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            System.out.println("authentication = " + authentication);
        }
        return "add-note";
    }

    @GetMapping("/users/super-admin")
    @Secured({"SUPER_ADMIN"})
    public String usersSuperAdmin(ServletRequest request, Authentication authentication) {

        return "redirect:/note/list";
    }
}
