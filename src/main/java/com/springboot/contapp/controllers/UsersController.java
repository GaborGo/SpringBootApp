package com.springboot.contapp.controllers;

import com.springboot.contapp.models.User;
import com.springboot.contapp.services.dto.UserDto;
import com.springboot.contapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
public class UsersController {

    private UserService userService;
    private static final String REDIRECT_USERS = "redirect:/users";

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getUsersHome(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", userService.convertToUserDtoList(users));
        model.addAttribute("newUser", new UserDto());
        return "index";
    }

    @PostMapping("deleteUser")
    public String deleteUser(@RequestParam("delete") int id) {
        userService.deleteUserById(id);
        return REDIRECT_USERS;
    }

    @PostMapping("addUser")
    public String addUser(@ModelAttribute("newUser") UserDto userDto) {
        userService.saveUser(userService.convertToUser(userDto));
        return REDIRECT_USERS;
    }

    @GetMapping("updateUser/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", userService.convertToUserDto(user));
        return "updateUser";
    }

    @PostMapping("saveUser/{id}")
    public String saveUser(@PathVariable("id") int id, @ModelAttribute("user") UserDto userDto) {
        userDto.setId(id);
        userService.saveUser(userService.convertToUser(userDto));
        return REDIRECT_USERS;
    }
}
