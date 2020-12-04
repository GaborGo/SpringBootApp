package com.springboot.contapp.services;

import com.springboot.contapp.controllers.UsersController;
import com.springboot.contapp.models.User;
import com.springboot.contapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsersController.class)
@AutoConfigureMockMvc(addFilters = false)
class TestUserService {

    @Test
    void getUserByIdTest() {
        UserRepository mockRepository = mock(UserRepository.class);
        when(mockRepository.findUserById(anyInt())).thenReturn(new User());
        UserService userService = new UserService();
        userService.setUserRepository(mockRepository);
        User user = userService.getUserById(100);
        assertNotNull(user);
    }

    @Test
    void throwExceptionForNonExistingUser() {
        UserRepository mockRepository = mock(UserRepository.class);
        when(mockRepository.findUserById(anyInt())).thenReturn(null);
        UserService userService = new UserService();
        userService.setUserRepository(mockRepository);
        try {
            userService.getUserById(100);
            fail("Exception should have been thrown");
        } catch (Exception e) {
            assertEquals("User with id: 100 not found!", e.getMessage());
        }
    }

}