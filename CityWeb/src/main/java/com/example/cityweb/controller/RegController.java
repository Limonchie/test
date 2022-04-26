package com.example.cityweb.controller;

import com.example.cityweb.entity.Role;
import com.example.cityweb.entity.User;
import com.example.cityweb.repository.UserRepo;
import com.example.cityweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegController {

    @Autowired
    UserRepo repo;
    UserService service;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/registration/save")
    public String saveNewUser(@RequestParam String email,@RequestParam String username,@RequestParam String password,Model model){
        User user = new User(email,username,password);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.Admin));
        repo.save(user);
        return "redirect:/login";
    }
}
