package com.goit.goit_notes.controller;

import com.goit.goit_notes.entity.UserAccountEntity;
import com.goit.goit_notes.service.UserAccountService;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class UserController {

    private final UserAccountService userAccountService;

    @GetMapping("/users")
    @Secured({"SUPER_ADMIN"})
    public String showAllUsers(Model model) {
        model.addAttribute("users", userAccountService.findAll());
        return "users";
    }
    //TODO 1. User Edit, Delete, Add form and functions
    //TODO 4. Add pagination
    //TODO 5. Add default start page

/*
    public List<String> users(ServletRequest request, Authentication authentication) {
        List<UserAccountEntity> userList = userAccountService.findAll();
        List<String> listUserNames = new ArrayList<>();
        for (UserAccountEntity user: userList
             ) {
            listUserNames.add(user.getUsername());
        }
        return listUserNames;
    }
*/

    @GetMapping("/users/admin")
    @Secured({"SUPER_ADMIN"})
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
