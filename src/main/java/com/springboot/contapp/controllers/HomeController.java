package com.springboot.contapp.controllers;

import com.springboot.contapp.models.User;
import com.springboot.contapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
       model.addAttribute("users", userService.getAllUsers());
       model.addAttribute("newUser", new User());
       userService.getAllUsers().forEach(n -> System.out.println(n.getUsername()));
       return "index";
    }

    @PostMapping("deleteUser")
    public String deleteUser(@RequestParam("delete") int id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @PostMapping("addUser")
    public String addUser(@ModelAttribute("newUser") User user) {
        System.out.println("EDITED: id:" + user.getId() +"  " + user.getUsername()+"  " + user.getPassword());
        userService.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("updateUser/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("saveUser/{id}")
    public String saveUser(@PathVariable("id") int id, @ModelAttribute("user") User user) {
        user.setId(id);
        System.out.println("EDITED:" + user.getUsername() + user.getPassword());
        userService.saveUser(user);
        return "redirect:/";
    }
}
