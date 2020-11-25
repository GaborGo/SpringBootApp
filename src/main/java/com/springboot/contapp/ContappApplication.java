package com.springboot.contapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ContappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContappApplication.class, args);
    }

}
