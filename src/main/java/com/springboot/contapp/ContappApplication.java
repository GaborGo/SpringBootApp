package com.springboot.contapp;

import com.springboot.contapp.models.User;
import com.springboot.contapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class ContappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContappApplication.class, args);
    }
}
