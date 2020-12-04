package com.springboot.contapp.controllers;

import com.springboot.contapp.models.User;
import com.springboot.contapp.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsersController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestUsersController{

    @MockBean
    private UserService mockService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void SuccessFindAllUsers() throws Exception {
        List<User> users = Arrays.asList(new User(), new User());
        when(mockService.getAllUsers()).thenReturn(users);
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.model().size(2))
                .andExpect(MockMvcResultMatchers.model().attribute("users",
                        mockService.convertToUserDtoList(users)))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

//    @Test
//    void testUpdateUser() throws Exception {
//        User user = new User(1,1,"user","pass", "email", "User");
//        when(mockService.getUserById(anyInt())).thenReturn(user);
//        this.mockMvc.perform(get("/users/updateUser/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("text/html;charset=UTF-8"))
//                .andExpect(MockMvcResultMatchers.model().size(1))
//                .andExpect(MockMvcResultMatchers.model().attribute("user", mockService.convertToUserDto(user)))
//                .andExpect(MockMvcResultMatchers.view().name("updateUser"));
//    }
}
