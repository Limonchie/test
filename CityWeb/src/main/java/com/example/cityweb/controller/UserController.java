package com.example.cityweb.controller;


import com.example.cityweb.entity.User;
import com.example.cityweb.exception.UserNotFoundException;
import com.example.cityweb.repository.UserRepo;
import com.example.cityweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user",new User());
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(@RequestParam String email,@RequestParam String username,@RequestParam String password,@RequestParam boolean active,Model model){
        User user = new User(email,username,password,active);
        userRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        try {
            User user = service.get(id);
            model.addAttribute ("user", user);
            model.addAttribute ("pageTitle", "EditUser");
            return "user_form";
        } catch (UserNotFoundException e) {
            e.printStackTrace ();
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        service.delete (id);
        return "redirect:/users";
    }
}
